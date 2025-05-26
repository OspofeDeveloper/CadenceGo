package com.example.cadence_sensor.utils

import com.example.cadence_sensor.models.ScannedSensorModel
import com.example.cadence_sensor.models.ScannedSensorsModel
import com.example.system.bluetooth.BleScanDto
import com.example.system.bluetooth.ScannedDeviceDto

fun BleScanDto.toDomain(): ScannedSensorsModel {
    return ScannedSensorsModel(
        sensors = scannedDevices.mapValues { it.value.toDomain() }
    )
}

fun ScannedDeviceDto.toDomain(): ScannedSensorModel {
    return ScannedSensorModel(
        name = name,
        rssi = rssi
    )
}