package com.example.weatherapp.search.mvp

import com.example.weatherapp.models.WeekModel

interface SearchView {

    fun showProgress()
    fun hideProgress()

    fun showTempToday(temp: String)
    fun showWeekWeather(listWeather: ArrayList<WeekModel>)

    fun showError(errorMessage: String)
    fun showTodayTitle()
    fun showTodayWeatherIcon(state: String)
}