package com.example.cadence_sensor.di

import com.example.cadence_sensor.datasources.ble.CadenceSensorBLEService
import com.example.cadence_sensor.repository.CadenceSensorRepository
import com.example.cadence_sensor.repository.CadenceSensorRepositoryImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataCadenceSensorModule = module {
    factoryOf(::CadenceSensorBLEService)

    single<CadenceSensorRepository> {
        CadenceSensorRepositoryImpl(get())
    }
}