package com.example.weatherapp.global

import com.example.weatherapp.models.TodayModel
import com.example.weatherapp.models.WeekModel
import io.reactivex.Single

interface DataManager {

    fun getTodayWeatherData(cityName: String, appId: String, units: String): Single<TodayModel>

    fun getWeekWeatherData(cityName: String, appId: String, units: String): Single<WeekModel>
}