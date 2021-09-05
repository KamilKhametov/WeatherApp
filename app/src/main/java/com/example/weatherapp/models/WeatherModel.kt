package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("list") val list: ArrayList<MainModel>,
    @SerializedName("cod") val code: String
)

data class MainModel(
    @SerializedName("temp") val temp: Float? = null
)