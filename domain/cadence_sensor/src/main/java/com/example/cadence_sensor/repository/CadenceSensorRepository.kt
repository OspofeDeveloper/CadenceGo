package com.example.cadence_sensor.repository

import com.example.cadence_sensor.models.ScannedSensorsModel
import com.example.common.AppError
import com.example.common.AppResult
import kotlinx.coroutines.flow.Flow

interface CadenceSensorRepository {
    suspend fun scanSensors(): Flow<AppResult<ScannedSensorsModel, AppError>>

    suspend fun connectToSensor()
}