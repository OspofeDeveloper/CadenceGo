package com.example.system.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanResult
import android.content.Context
import android.os.ParcelUuid

fun Context.provideBluetoothAdapter(): BluetoothAdapter? {
    val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as? BluetoothManager
    return bluetoothManager?.adapter
}

fun BluetoothDevice.toScannedDeviceDto(rssi: Int): ScannedDeviceDto {
    return ScannedDeviceDto(
        name = name ?: "",
        rssi = rssi
    )
}

fun Int.toBleServiceUuid(): String {
    return String.format(
        "0000%04X-0000-1000-8000-00805F9B34FB",
        this and 0xFFFF
    )
}

fun ScanResult.hasServiceUuid(uuid: ParcelUuid): Boolean {
    return scanRecord?.serviceUuids?.contains(uuid) == true
}

