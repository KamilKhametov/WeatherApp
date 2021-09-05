package com.example.weatherapp.search.mvp

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.weatherapp.global.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

@InjectViewState
class SearchPresenter @Inject constructor(
    private val api: DataManager
) : MvpPresenter<SearchView>() {

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getSearchCityWeather(cityName: String, appId: String) {
        compositeDisposable += api.getWeatherData(cityName, appId)
            .doOnSubscribe { viewState.showProgress() }
            .doAfterTerminate { viewState.hideProgress() }
            .subscribeBy(onSuccess = { list ->
//                list.list.forEach {
//                    viewState.showData(it.temp.toString())
//                }
                viewState.showData(list.code)
            },
                onError = {

                })
    }
}