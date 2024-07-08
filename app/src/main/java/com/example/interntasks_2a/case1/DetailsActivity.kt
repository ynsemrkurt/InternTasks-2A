package com.example.interntasks_2a.case1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.Constants.CITY_KEY
import com.example.interntasks_2a.Constants.DEGREE_KEY
import com.example.interntasks_2a.Constants.STATUS_KEY
import com.example.interntasks_2a.WeatherUtils
import com.example.interntasks_2a.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val degree = intent.getIntExtra(DEGREE_KEY, 0)
        val city = intent.getStringExtra(CITY_KEY)
        val status = intent.getStringExtra(STATUS_KEY)

        WeatherUtils.bindWeatherData(
            binding.textViewDegree,
            binding.textViewCity,
            binding.textViewStatus,
            binding.imageViewStatus,
            degree,
            city.toString(),
            status,
            this
        )
    }
}