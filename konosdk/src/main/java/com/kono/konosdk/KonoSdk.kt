package com.kono.konosdk

import com.kono.konosdk.api.ApiManager

class KonoSdk {
    val apiManager by lazy {
        ApiManager()
    }
}