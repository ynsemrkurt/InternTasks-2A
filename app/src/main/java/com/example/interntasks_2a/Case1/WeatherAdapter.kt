package com.example.interntasks_2a.Case1

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
    ): WeatherAdapter.WeatherViewHolder {
        val binding = ItemWeatherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
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

            val statusDrawableRes = fetchImage(weather.status)
            setImage(statusDrawableRes)

            itemView.setOnClickListener {
                goDetailsPage(weather)
            }
        }

        @DrawableRes
        private fun fetchImage(status: String): Int {
            val context = itemView.context
            return when (status) {
                context.getString(R.string.sunny) -> R.drawable.ic_sunny
                context.getString(R.string.cloudy) -> R.drawable.ic_cloudy
                context.getString(R.string.rainy) -> R.drawable.ic_rainy
                context.getString(R.string.snowy) -> R.drawable.ic_snowy
                else -> R.drawable.ic_sunny
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