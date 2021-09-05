package com.example.weatherapp.global

import com.example.weatherapp.models.WeatherModel
import com.example.weatherapp.network.WeatherApi
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DataManagerImpl @Inject constructor(
    private val api: WeatherApi
) : DataManager {

    override fun getWeatherData(cityName: String, appId: String): Single<WeatherModel> =
        api.getSearchCity(cityName, appId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}