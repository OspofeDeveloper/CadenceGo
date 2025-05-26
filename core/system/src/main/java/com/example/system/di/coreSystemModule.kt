package com.example.system.di

import android.bluetooth.BluetoothAdapter
import com.example.system.bluetooth.BluetoothDataSource
import com.example.system.bluetooth.provideBluetoothAdapter
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreSystemModule = module {
    single<BluetoothAdapter?> { androidContext().provideBluetoothAdapter() }

    singleOf(::BluetoothDataSource)
}