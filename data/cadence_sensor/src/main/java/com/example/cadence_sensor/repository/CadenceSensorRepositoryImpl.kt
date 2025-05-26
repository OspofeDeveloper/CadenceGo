package com.example.cadence_sensor.repository

import com.example.cadence_sensor.models.ScannedSensorsModel
import com.example.cadence_sensor.utils.Constants.SHORT_CADENCE_SENSOR_SERVICE_UUID
import com.example.cadence_sensor.utils.toDomain
import com.example.common.AppError
import com.example.common.AppResult
import com.example.common.mapResult
import com.example.system.bluetooth.BluetoothDataSource
import com.example.system.bluetooth.toBleServiceUuid
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CadenceSensorRepositoryImpl(
    private val bluetoothSystemManager: BluetoothDataSource
) : CadenceSensorRepository {

    override suspend fun scanSensors() : Flow<AppResult<ScannedSensorsModel, AppError>> {
        val serviceUuid = SHORT_CADENCE_SENSOR_SERVICE_UUID.toBleServiceUuid()

        return bluetoothSystemManager.startScan(serviceUuid).map { result ->
            result.mapResult { it.toDomain() }
        }
    }

    override suspend fun connectToSensor() {

    }

}