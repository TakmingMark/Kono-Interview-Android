package com.kono.remote_interview_android.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kono.konosdk.KonoSdk
import com.kono.konosdk.data.Articles
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber

class ArticleViewModel(private val konoSdk: KonoSdk) : ViewModel() {

    private val _callArticlesDataFailure = MutableLiveData<String>()
    val callArticlesDataFailure: LiveData<String> = _callArticlesDataFailure

    private val _updateArticlesData = MutableLiveData<Articles>()
    val updateArticlesData: LiveData<Articles> = _updateArticlesData

    fun callArticlesData() {
        konoSdk
            .apiManager
            .getArticles()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({ response ->
                if (response.isSuccessful) {
                    Timber.d("get articles success")
                    val articles = response.body()
                    Timber.d("articles:$articles")
                    if (articles != null)
                        _updateArticlesData.postValue(articles!!)
                } else {
                    _callArticlesDataFailure.postValue("Response data is failure.")
                }
            }, { throwable ->
                _callArticlesDataFailure.postValue(throwable.message)
            })
    }
}