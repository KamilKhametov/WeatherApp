package com.example.weatherapp.global

import com.example.weatherapp.models.WeatherModel
import io.reactivex.Single

interface DataManager {

    fun getTodayWeatherData(cityName: String, appId: String, units: String): Single<WeatherModel>
}