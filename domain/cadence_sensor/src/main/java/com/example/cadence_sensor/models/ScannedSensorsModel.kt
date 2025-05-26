package com.example.cadence_sensor.models

data class ScannedSensorsModel(
    val sensors: Map<String, ScannedSensorModel> = emptyMap()
)

data class ScannedSensorModel(
    val name: String = "",
    val rssi: Int = 0,
)
