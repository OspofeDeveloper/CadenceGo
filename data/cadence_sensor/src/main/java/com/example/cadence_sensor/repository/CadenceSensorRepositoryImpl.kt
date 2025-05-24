package com.example.cadence_sensor.repository

import com.example.cadence_sensor.datasources.ble.CadenceSensorBLEService

class CadenceSensorRepositoryImpl(
    private val cadenceSensorBleService: CadenceSensorBLEService
) : CadenceSensorRepository {

}