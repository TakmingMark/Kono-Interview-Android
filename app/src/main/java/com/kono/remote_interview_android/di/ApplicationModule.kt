package com.kono.remote_interview_android.di

import com.kono.remote_interview_android.helper.UrlHelper
import com.kono.remote_interview_android.ui.article.recyclerview.ArticleAdapter
import org.koin.dsl.module

val applicationModule = module {
    single {
        UrlHelper()
    }

    factory {
        ArticleAdapter(get())
    }
}