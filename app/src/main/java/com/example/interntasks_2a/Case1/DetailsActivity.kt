package com.example.interntasks_2a.Case1

import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.R
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
            val statusDrawableRes = fetchImage(it)
            setImage(statusDrawableRes)
        } ?: run {
            Log.e("Null Error", "status is null")
        }
    }

    @DrawableRes
    private fun fetchImage(status: String): Int {
        return when (status) {
            getString(R.string.sunny) -> R.drawable.ic_sunny
            getString(R.string.cloudy) -> R.drawable.ic_cloudy
            getString(R.string.rainy) -> R.drawable.ic_rainy
            getString(R.string.snowy) -> R.drawable.ic_snowy
            else -> R.drawable.ic_sunny
        }
    }

    private fun setImage(@DrawableRes image: Int) {
        binding.imageViewStatus.setImageResource(image)
    }
}