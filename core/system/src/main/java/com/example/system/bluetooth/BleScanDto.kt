package com.example.system.bluetooth

data class BleScanDto(
    val scannedDevices: Map<String, ScannedDeviceDto> = emptyMap()
)

data class ScannedDeviceDto(
    val name: String = "",
    val rssi: Int = 0
)