package com.kono.konosdk.api

import com.kono.konosdk.retrofit.KonoRetrofit

class ApiManager {
    private val konoRetrofit by lazy {
        KonoRetrofit()
    }


}