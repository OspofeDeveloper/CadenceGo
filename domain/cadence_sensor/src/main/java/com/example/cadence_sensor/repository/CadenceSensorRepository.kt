package com.example.cadence_sensor.repository

import kotlinx.coroutines.flow.Flow

interface CadenceSensorRepository {
    suspend fun getCadence(): Flow<String>

}