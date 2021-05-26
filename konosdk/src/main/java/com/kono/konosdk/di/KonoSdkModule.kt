package com.kono.konosdk.di

import com.kono.konosdk.KonoSdk
import org.koin.dsl.module


val konoSdkModule = module {
    single {
        KonoSdk()
    }
}