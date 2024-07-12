package com.example.interntasks_2a

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.interntasks_2a.databinding.ItemWeatherBinding

class WeatherAdapter(
    private val weatherList: List<Weather>,
    private val itemClickListener: (Weather) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    inner class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    itemClickListener(weatherList[adapterPosition])
                }
            }
        }

        fun bind(weather: Weather) {
            binding.textViewDegree.text = "${weather.degree}°"
            binding.textViewDegreeBw.text = "${weather.degreeMin}° - ${weather.degreeMax}°"
            binding.textViewCity.text = weather.city
            binding.textViewStatus.text = weather.status

            val weatherStatus = WeatherStatus.fromString(itemView.context, weather.status)
            val statusDrawableRes = fetchImage(weatherStatus)
            setImage(statusDrawableRes)
        }

        @DrawableRes
        private fun fetchImage(status: WeatherStatus): Int {
            return when (status) {
                WeatherStatus.SUNNY -> R.drawable.ic_sunny
                WeatherStatus.CLOUDY -> R.drawable.ic_cloudy
                WeatherStatus.RAINY -> R.drawable.ic_rainy
                WeatherStatus.SNOWY -> R.drawable.ic_snowy
                WeatherStatus.UNKNOWN -> R.drawable.ic_sunny
            }
        }

        private fun setImage(@DrawableRes image: Int) {
            binding.imageViewStatus.setImageResource(image)
        }
    }
}