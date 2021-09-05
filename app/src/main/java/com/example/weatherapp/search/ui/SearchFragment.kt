package com.example.weatherapp.search.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.weatherapp.App
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.global.DataManager
import com.example.weatherapp.search.mvp.SearchPresenter
import com.example.weatherapp.search.mvp.SearchView
import javax.inject.Inject

class SearchFragment : Fragment(), SearchView {

    private var _binding: FragmentSearchBinding? = null
    private val viewBinding get() = _binding!!

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    @InjectPresenter
    lateinit var presenter: SearchPresenter

    @ProvidePresenter
    fun providePresenter() = presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getSearchCityWeather("Moscow", "057a46488b92c9010d3d6feac8ceb493")
    }

    override fun showProgress() {
        viewBinding.progressBarSearch.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        viewBinding.progressBarSearch.visibility = View.GONE
    }

    override fun showData(text: String) {
        viewBinding.textExample.text = text
    }
}