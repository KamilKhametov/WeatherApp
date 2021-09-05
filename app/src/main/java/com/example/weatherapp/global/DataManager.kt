package com.example.weatherapp.global

import com.example.weatherapp.models.WeatherModel
import io.reactivex.Single

interface DataManager {

    fun getWeatherData(cityName: String, appId: String): Single<WeatherModel>
}