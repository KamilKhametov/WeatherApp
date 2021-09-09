package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeekModel(
    @SerializedName("list") val testMainList: ArrayList<MainWeekWeatherState>,
){

    class MainWeekWeatherState(
        @SerializedName("weather") val mainWeekStateList: ArrayList<WeekWeatherState>,
        @SerializedName("dt_txt") val date: String,
        @SerializedName("main") val tempModel: MainTemp
    )

    class WeekWeatherState(
        @SerializedName("id") val id: String,
        @SerializedName("main") val weatherState: String
    )

    class MainTemp(
        @SerializedName("temp") val temp: Float
    )
}