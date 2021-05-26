package com.kono.konosdk.api

import com.kono.konosdk.data.Articles
import com.kono.konosdk.retrofit.KonoRetrofit
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class ApiManager {
    private val konoRetrofit by lazy {
        KonoRetrofit()
    }

    fun getArticles(): Single<Response<Articles>> {
        return konoRetrofit
            .service
            .getArticles()
    }
}