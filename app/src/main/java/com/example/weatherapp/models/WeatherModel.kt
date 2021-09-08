package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("main") val todayModel: MainTodayModel,
    @SerializedName("list") val weakWeatherList: List<MainWeekModel>
)

data class MainTodayModel(
    // id и так далее
    @SerializedName("temp") val temp: Float
)

data class WeekWeather(
    @SerializedName("main") val main: MainWeekModel
)

data class MainWeekModel(
    // TODO: id и так далее
    @SerializedName("temp") val temp: Float? = null
)
