package com.example.cadence_sensor.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CadenceSensorRepositoryImpl : CadenceSensorRepository {
    override suspend fun getCadence(): Flow<String> {
        return flowOf("123")
    }
}