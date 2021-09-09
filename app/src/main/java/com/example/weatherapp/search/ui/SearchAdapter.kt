package com.example.weatherapp.search.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemSearchBinding
import com.example.weatherapp.models.WeekModel

class SearchAdapter(
    private val dayOfWeek: String
) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    private val weatherList = arrayListOf<WeekModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder =
        SearchViewHolder(ItemSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    fun setData(listWeather: ArrayList<WeekModel>){
        this.weatherList.run {
            clear()
            addAll(listWeather)
        }

        notifyDataSetChanged()
    }

    inner class SearchViewHolder(private var itemBinding: ItemSearchBinding): RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(item: WeekModel){

            with(itemBinding){

                item.testMainList.forEach {
                    tempTitle.text = it.tempModel.temp.toInt().toString() + GRADUS_TEXT
                }

                item.testMainList.forEach {
                    dateTitle.text = it.date
                }

                item.testMainList.forEach {
                    it.mainWeekStateList.forEach { state ->
                        when(state.weatherState){
                            CLOUDS -> iconWeather.setImageResource(R.drawable.ic_cloud)
                            RAIN -> iconWeather.setImageResource(R.drawable.ic_rain)
                        }
                    }
                }

                dayTitle.text = dayOfWeek
            }
        }
    }

    private companion object{
        const val GRADUS_TEXT = "°"
        const val CLOUDS = "Clouds"
        const val RAIN = "Rain"
    }
}