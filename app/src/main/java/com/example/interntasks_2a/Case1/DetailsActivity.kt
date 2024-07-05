package com.example.interntasks_2a.Case1

import android.graphics.Bitmap
import android.media.Image
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.interntasks_2a.R
import com.example.interntasks_2a.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val degree = intent.getIntExtra("degree", 0)
        val city = intent.getStringExtra("city")
        val status = intent.getStringExtra("status")

        binding.textViewDegree.text = "$degree°"
        binding.textViewCity.text = city
        binding.textViewStatus.text = status
        fetchImage(status!!)
    }

    private fun fetchImage(status: String) {
        seImage(
            when (status) {
                "Güneşli" -> R.drawable.ic_sunny
                "Bulutlu" -> R.drawable.ic_cloudy
                "Yağmurlu" -> R.drawable.ic_rainy
                "Karlı" -> R.drawable.ic_snowy
                else -> R.drawable.ic_sunny
            }
        )
    }

    private fun seImage(image:Int){
        binding.imageViewStatus.setImageResource(image)
    }
}