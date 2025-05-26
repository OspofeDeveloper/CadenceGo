package com.example.system.bluetooth

import com.example.common.AppError

enum class BleError : AppError {
    START_SCAN_FAILED,
    SCAN_TIMEOUT
}