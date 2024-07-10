package com.example.interntasks_2a.case1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.Constants.CITY_KEY
import com.example.interntasks_2a.Constants.DEGREE_KEY
import com.example.interntasks_2a.Constants.STATUS_KEY
import com.example.interntasks_2a.Weather
import com.example.interntasks_2a.WeatherAdapter
import com.example.interntasks_2a.databinding.ActivityListBinding
import com.example.interntasks_2a.weatherList

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = WeatherAdapter(weatherList) { weather -> goDetailsPage(weather) }
        binding.recyclerViewWeather.adapter = adapter
    }

    private fun goDetailsPage(weather: Weather) {
        val intent = Intent(this@ListActivity, DetailsActivity::class.java)
        intent.putExtra(DEGREE_KEY, weather.degree)
        intent.putExtra(CITY_KEY, weather.city)
        intent.putExtra(STATUS_KEY, weather.status)
        startActivity(intent)
    }
}