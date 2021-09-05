package com.example.weatherapp.di.component

import com.example.weatherapp.App
import com.example.weatherapp.MainActivity
import com.example.weatherapp.di.module.NavigationModule
import com.example.weatherapp.di.module.NetworkModule
import com.example.weatherapp.search.ui.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NavigationModule::class, NetworkModule::class])
@Singleton
interface AppComponent {

    fun inject(app: App)
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: SearchFragment)
}