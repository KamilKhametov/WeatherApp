package com.example.weatherapp.search.mvp

interface SearchView {

    fun showProgress()
    fun hideProgress()

    fun showTempToday(temp: String)

    fun showError(errorMessage: String)
}