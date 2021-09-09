package com.example.weatherapp.network

import com.example.weatherapp.models.TodayModel
import com.example.weatherapp.models.WeekModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/weather")
    fun getTodayWeather(
        @Query("q") city: String,
        @Query("appId") appId: String,
        @Query("units") units: String
    ): Single<TodayModel>

    @GET("data/2.5/forecast")
    fun getWeekWeather(
        @Query("q") city: String,
        @Query("appId") appId: String,
        @Query("units") units: String
    ): Single<WeekModel>
}