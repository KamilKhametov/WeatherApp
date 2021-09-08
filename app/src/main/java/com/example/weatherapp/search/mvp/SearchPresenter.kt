package com.example.weatherapp.search.mvp

import com.example.weatherapp.global.DataManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class SearchPresenter @Inject constructor(
    private val api: DataManager
) {

    private var viewState: SearchView? = null
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun viewAttach(view: SearchView) {
        this.viewState = view
    }

    fun getSearchCityWeather(cityName: String, appId: String, units: String) {
        compositeDisposable += api.getTodayWeatherData(cityName, appId, units)
            .doOnSubscribe { viewState?.showProgress() }
            .doAfterTerminate { viewState?.hideProgress() }
            .subscribeBy(
                onSuccess = { item ->
                    viewState?.showTempToday(item.todayModel.temp.toInt().toString())
                },
                onError = {
                    // TODO: решить, как правильно выдавать ошибку
//                    viewState?.showError("")
                })
    }
}