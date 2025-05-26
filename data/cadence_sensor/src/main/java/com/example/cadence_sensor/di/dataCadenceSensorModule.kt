package com.example.cadence_sensor.di

import com.example.cadence_sensor.datasources.ble.CadenceSensorService
import com.example.cadence_sensor.repository.CadenceSensorRepository
import com.example.cadence_sensor.repository.CadenceSensorRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataCadenceSensorModule = module {
    singleOf(::CadenceSensorService)

    single<CadenceSensorRepository> {
        CadenceSensorRepositoryImpl(get())
    }
}