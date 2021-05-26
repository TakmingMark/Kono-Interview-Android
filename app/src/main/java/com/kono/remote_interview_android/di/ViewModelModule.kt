package com.kono.remote_interview_android.di

import com.kono.remote_interview_android.MainActivityViewModel
import com.kono.remote_interview_android.ui.article.ArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainActivityViewModel()
    }

    viewModel {
        ArticleViewModel(get())
    }
}