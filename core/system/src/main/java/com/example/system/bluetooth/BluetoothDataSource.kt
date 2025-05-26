package com.example.system.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.le.BluetoothLeScanner
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.os.ParcelUuid
import com.example.common.AppError
import com.example.common.AppResult
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import java.util.UUID

class BluetoothDataSource(
    private val bluetoothAdapter: BluetoothAdapter?
) {
    private val bleScanner: BluetoothLeScanner?
        get() = bluetoothAdapter?.bluetoothLeScanner

    fun startScan(serviceUuid: String): Flow<AppResult<BleScanDto, AppError>> = callbackFlow {
        val scannedDevices = mutableMapOf<String, ScannedDeviceDto>()
        val serviceUuid = ParcelUuid(UUID.fromString(serviceUuid))

        val callback = object : ScanCallback() {
            override fun onScanResult(callbackType: Int, result: ScanResult) {
                val deviceAddress = result.device.address
                if(scannedDevices.containsKey(deviceAddress)) return

                if (result.hasServiceUuid(serviceUuid)) {
                    scannedDevices[deviceAddress] = result.device.toScannedDeviceDto(result.rssi)
                    trySend(AppResult.Success(BleScanDto(scannedDevices)))
                }
            }

            override fun onScanFailed(errorCode: Int) {
                trySend(AppResult.Error(BleError.START_SCAN_FAILED))
                close()
            }
        }

        bleScanner?.startScan(callback)

        val timeoutJob = launch {
            delay(10000)
            bleScanner?.stopScan(callback)
            trySend(AppResult.Error(BleError.SCAN_TIMEOUT))
            close()
        }

        awaitClose {
            bleScanner?.stopScan(callback)
            timeoutJob.cancel()
        }
    }
}