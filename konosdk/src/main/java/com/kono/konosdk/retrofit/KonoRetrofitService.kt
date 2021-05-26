package com.kono.konosdk.retrofit

import com.kono.konosdk.data.Articles
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET

interface KonoRetrofitService {
    companion object {
        private const val SERVICE_PLATFORM = "platform"
        private const val SERVICE_API = "api"
        private const val SERVICE_VERSION = "v1"
        private const val PATH_PROJECTS = "projects"
        private const val PATH_USER_ID = "5f869aed6f3848deb4632d35" //I not sure
        private const val PATH_FOLDERS = "folders"
        private const val PATH_ARTICLES_ID = "5f869ead6f38484d15632d51" // I no sure
    }

    @GET("$SERVICE_PLATFORM/$SERVICE_API/$SERVICE_VERSION/$PATH_PROJECTS/$PATH_USER_ID/$PATH_FOLDERS/$PATH_ARTICLES_ID")
    fun getArticles(): Single<Response<Articles>>
}