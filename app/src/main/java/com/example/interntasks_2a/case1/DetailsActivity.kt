package com.example.interntasks_2a.case1

import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.R
import com.example.interntasks_2a.WeatherStatus
import com.example.interntasks_2a.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    companion object {
        const val DEGREE_KEY = "degree"
        const val CITY_KEY = "city"
        const val STATUS_KEY = "status"
    }

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val degree = intent.getIntExtra(DEGREE_KEY, 0)
        val city = intent.getStringExtra(CITY_KEY)
        val status = intent.getStringExtra(STATUS_KEY)

        binding.textViewDegree.text = "$degree°"
        binding.textViewCity.text = city
        binding.textViewStatus.text = status
        status?.let {
            val weatherStatus = WeatherStatus.fromString(this,it)
            val statusDrawableRes = fetchImage(weatherStatus)
            setImage(statusDrawableRes)
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

    private fun setImage(@DrawableRes image: Int) {
        binding.imageViewStatus.setImageResource(image)
    }
}