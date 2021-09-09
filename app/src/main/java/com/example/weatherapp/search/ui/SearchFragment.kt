package com.example.weatherapp.search.ui

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.weatherapp.App
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.global.DataManager
import com.example.weatherapp.models.WeekModel
import com.example.weatherapp.search.mvp.SearchPresenter
import com.example.weatherapp.search.mvp.SearchView
import javax.inject.Inject
import kotlin.collections.ArrayList

class SearchFragment : Fragment(), SearchView {

    private var _binding: FragmentSearchBinding? = null
    private val viewBinding get() = _binding!!

    @Inject
    lateinit var dataManager: DataManager

    @Inject
    lateinit var presenter: SearchPresenter

    private val searchAdapter by lazy(LazyThreadSafetyMode.NONE) {
        SearchAdapter(getDayOfWeek())
    }

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

        initEdiTextFocusChangeListener()
        getSearchCity()
        showTodayTitle()
    }

    private fun getSearchCity() {
        with(viewBinding) {
            val inputCityTextWatcher = (object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

                override fun afterTextChanged(p0: Editable?) {
                    presenter.getSearchCityWeather(inputCityEditText.text.toString(), APP_ID, METRIC_TEXT)
                    presenter.getSearchCityWeekWeather(inputCityEditText.text.toString(), APP_ID, METRIC_TEXT)
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

    override fun showWeekWeather(listWeather: ArrayList<WeekModel>) {
        searchAdapter.setData(listWeather)
        viewBinding.weatherWeekRecyclerView.adapter = searchAdapter
    }

    override fun showError(errorMessage: String) {
        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
    }

    override fun showTodayTitle() {
        with(viewBinding){
            titleTodayText.isVisible = inputCityEditText.text.toString().isNotEmpty()
            tempTodayText.isVisible = inputCityEditText.text.toString().isNotEmpty()
            iconWeatherSearch.isVisible = inputCityEditText.text.toString().isNotEmpty()
            weatherWeekRecyclerView.isVisible = inputCityEditText.text.toString().isNotEmpty()
        }
    }

    override fun showTodayWeatherIcon(state: String) {
        with(viewBinding) {
            when (state) {
                "Clouds" -> iconWeatherSearch.setImageResource(R.drawable.img_1)
                "Rain" -> iconWeatherSearch.setImageResource(R.drawable.img)
            }
        }
    }

    // TODO: получать текущий день недели
    private fun getDayOfWeek(): String = "Понедельник"

    private fun initEdiTextFocusChangeListener(){
            viewBinding.inputCityEditText.setOnFocusChangeListener { view, hasFocus ->
                if(!hasFocus){
                    hideKeyboard()
                }
            }
    }

    private fun hideKeyboard(){
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        inputMethodManager?.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private companion object {
        const val GRADUS_TEXT = "°С"
        const val APP_ID = "057a46488b92c9010d3d6feac8ceb493"
        const val METRIC_TEXT = "metric"
    }
}