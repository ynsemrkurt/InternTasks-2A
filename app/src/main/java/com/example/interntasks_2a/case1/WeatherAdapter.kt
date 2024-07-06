package com.example.interntasks_2a.case1

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.recyclerview.widget.RecyclerView
import com.example.interntasks_2a.R
import com.example.interntasks_2a.databinding.ItemWeatherBinding

class WeatherAdapter(private val weatherList: List<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(private val binding: ItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.textViewDegree.text = "${weather.degree}°"
            binding.textViewDegreeBw.text = "${weather.degreeMin}° - ${weather.degreeMax}°"
            binding.textViewCity.text = weather.city
            binding.textViewStatus.text = weather.status

            val weatherStatus = WeatherStatus.fromString(itemView.context,weather.status)
            val statusDrawableRes = fetchImage(weatherStatus)
            setImage(statusDrawableRes)

            itemView.setOnClickListener {
                goDetailsPage(weather)
            }
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

        private fun goDetailsPage(weather: Weather) {
            val context = itemView.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra(context.getString(R.string.key_degree), weather.degree)
            intent.putExtra(context.getString(R.string.key_city), weather.city)
            intent.putExtra(context.getString(R.string.key_status), weather.status)
            context.startActivity(intent)
        }
    }
}