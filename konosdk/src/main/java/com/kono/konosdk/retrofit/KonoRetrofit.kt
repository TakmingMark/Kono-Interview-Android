package com.kono.konosdk.retrofit

import com.google.gson.Gson
import com.kono.konosdk.interceptor.UserAgentInterceptor
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class KonoRetrofit {
    companion object {
        private const val HTTP_SCHEME = "https"
        private const val BASE_URL = "kps-server-ojx42ulvaa-uc.a.run.app"
        private const val USER_AGENT = "interviewer_user_agent"
        private const val TIME_OUT = 10L
    }

    val service by lazy {
        createService()
    }

    private fun createService(): KonoRetrofitService {
        return Retrofit
            .Builder()
            .baseUrl(getHttpUrl())
            .addConverterFactory(getGsonConverterFactory())
            .addCallAdapterFactory(getRxJava3CallAdapterFactory())
            .client(getOkHttpClient())
            .build()
            .create(KonoRetrofitService::class.java)
    }

    private fun getHttpUrl(): HttpUrl {
        return HttpUrl.Builder()
            .scheme(HTTP_SCHEME)
            .host(BASE_URL)
            .build()
    }

    private fun getGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    private fun getRxJava3CallAdapterFactory(): RxJava3CallAdapterFactory {
        return RxJava3CallAdapterFactory.create()
    }

    private fun getOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val userAgentInterceptor = UserAgentInterceptor(USER_AGENT)

        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .addInterceptor(userAgentInterceptor)
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .build()
    }
}