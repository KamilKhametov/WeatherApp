package com.example.weatherapp.models

import com.google.gson.annotations.SerializedName

data class TodayModel(
    @SerializedName("main") val todayModel: MainTodayModel,
    @SerializedName("weather") val todayWeatherState: List<TodayWeatherState>
) {

    class MainTodayModel(
        @SerializedName("temp") val temp: Float
    )

    class TodayWeatherState(
        @SerializedName("main") val state: String
    )
}
