package com.example.weatherapp.screen

import com.example.weatherapp.search.ui.SearchFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {

    fun searchFragment() = FragmentScreen {
        SearchFragment()
    }
}