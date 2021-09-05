package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.component.AppComponent
import com.example.weatherapp.di.component.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder().build()
        appComponent.inject(this)
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}