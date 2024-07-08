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

class ListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weatherList = listOf(
            Weather(10, 5, 15, "İstanbul", "Güneşli"),
            Weather(15, 10, 20, "Ankara", "Bulutlu"),
            Weather(20, 15, 25, "İzmir", "Yağmurlu"),
            Weather(25, 20, 30, "Antalya", "Karlı"),
            Weather(30, 25, 35, "Bursa", "Güneşli"),
            Weather(35, 30, 40, "Konya", "Bulutlu"),
            Weather(40, 35, 45, "Diyarbakır", "Yağmurlu"),
            Weather(45, 40, 50, "Edirne", "Karlı")
        )

        val adapter = WeatherAdapter(weatherList)
        binding.recyclerViewWeather.adapter = adapter

        adapter.setOnItemClickListener(object : WeatherAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                goDetailsPage(weatherList[position])
            }
        })
    }

    private fun goDetailsPage(weather: Weather) {
        val intent = Intent(this@ListActivity, DetailsActivity::class.java)
        intent.putExtra(DEGREE_KEY, weather.degree)
        intent.putExtra(CITY_KEY, weather.city)
        intent.putExtra(STATUS_KEY, weather.status)
        startActivity(intent)
    }
}