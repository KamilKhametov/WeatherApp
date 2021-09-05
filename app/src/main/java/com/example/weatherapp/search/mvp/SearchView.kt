package com.example.weatherapp.search.mvp

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType


@StateStrategyType(AddToEndSingleStrategy::class)
interface SearchView : MvpView {

    fun showProgress()
    fun hideProgress()

    fun showData(text: String)
}