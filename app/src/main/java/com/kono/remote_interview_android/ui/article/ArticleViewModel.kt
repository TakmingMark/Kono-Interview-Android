package com.kono.remote_interview_android.ui.article

import androidx.lifecycle.ViewModel
import com.kono.konosdk.KonoSdk
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val konoSdk: KonoSdk) : ViewModel() {

    fun test() {
        konoSdk
            .apiManager
            .getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe { response ->
                if (response.isSuccessful) {
                    Timber.d("get articles success")
                    val articles = response.body()
                    Timber.d("articles:$articles")
                }
            }
    }
}