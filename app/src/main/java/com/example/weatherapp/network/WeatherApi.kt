package com.example.weatherapp.network

import com.example.weatherapp.models.WeatherModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("https://api.openweathermap.org/data/2.5/forecast?q=Moscow&appid=057a46488b92c9010d3d6feac8ceb493&units=metric")
    fun getSearchCity(
        @Query("q") city: String,
        @Query("appId") appId: String
    ): Single<WeatherModel>
}