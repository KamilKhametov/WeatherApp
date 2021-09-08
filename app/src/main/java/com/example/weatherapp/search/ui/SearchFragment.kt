package com.example.weatherapp.search.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
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
    lateinit var presenter: SearchPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.inject(this)
        super.onCreate(savedInstanceState)

        presenter.viewAttach(this)
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

        getSearchCity()
    }

    private fun getSearchCity() {
        with(viewBinding) {
            val inputCityTextWatcher = (object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    presenter.getSearchCityWeather(inputCityEditText.text.toString(), APP_ID, METRIC_TEXT)
                }
            })
            inputCityEditText.addTextChangedListener(inputCityTextWatcher)
        }
    }

    override fun showProgress() {
        viewBinding.progressBarSearch.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        viewBinding.progressBarSearch.visibility = View.GONE
    }

    override fun showTempToday(temp: String) {
        viewBinding.tempTodayText.text = temp + GRADUS_TEXT
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    private companion object {
        const val GRADUS_TEXT = "°"
        const val APP_ID = "057a46488b92c9010d3d6feac8ceb493"
        const val METRIC_TEXT = "metric"
    }
}