package com.example.interntasks_2a

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes

object WeatherUtils {

    fun bindWeatherData(
        textViewDegree: TextView,
        textViewCity: TextView,
        textViewStatus: TextView,
        imageViewStatus: ImageView,
        degree: Int,
        city: String,
        status: String?,
        context: Context
    ) {
        textViewDegree.text = "$degree°"
        textViewCity.text = city
        textViewStatus.text = status
        status?.let {
            val weatherStatus = WeatherStatus.fromString(context, it)
            val statusDrawableRes = fetchImage(weatherStatus)
            setImage(imageViewStatus, statusDrawableRes)
        } ?: run {
            Log.e("Null Error", "status is null")
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

    private fun setImage(imageViewStatus: ImageView, @DrawableRes image: Int) {
        imageViewStatus.setImageResource(image)
    }
}