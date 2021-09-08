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

    override fun getTodayWeatherData(cityName: String, appId: String, units: String): Single<WeatherModel> =
        api.getTodayWeather(cityName, appId, units)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}