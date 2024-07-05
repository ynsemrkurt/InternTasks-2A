package com.example.interntasks_2a.Case1

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.interntasks_2a.R

class WeatherAdapter(private val weatherList: List<Weather>) :
    RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeatherAdapter.WeatherViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false)
        return WeatherViewHolder(view)
    }

    override fun onBindViewHolder(holder: WeatherAdapter.WeatherViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewDegree = itemView.findViewById<TextView>(R.id.textViewDegree)
        private val textViewDegreeBw = itemView.findViewById<TextView>(R.id.textViewDegreeBw)
        private val textViewCity = itemView.findViewById<TextView>(R.id.textViewCity)
        private val imageViewStatus = itemView.findViewById<ImageView>(R.id.imageViewStatus)
        private val textViewStatus = itemView.findViewById<TextView>(R.id.textViewStatus)

        fun bind(weather: Weather) {
            textViewDegree.text = weather.degree.toString()
            textViewDegreeBw.text = "${weather.degreeMin} - ${weather.degreeMax}°"
            textViewCity.text = weather.city
            textViewStatus.text = weather.status

            fetchImage(weather.status, imageViewStatus)

            itemView.setOnClickListener {
                goDetailsPage(weather)
            }
        }


        private fun fetchImage(status: String, imageViewStatus: ImageView) {
            when (status) {
                "Güneşli" -> imageViewStatus.setImageResource(R.drawable.ic_sunny)
                "Bulutlu" -> imageViewStatus.setImageResource(R.drawable.ic_cloudy)
                "Yağmurlu" -> imageViewStatus.setImageResource(R.drawable.ic_rainy)
                "Karlı" -> imageViewStatus.setImageResource(R.drawable.ic_snowy)
            }
        }

        private fun goDetailsPage(weather: Weather){
            val context = itemView.context
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("degree", weather.degree)
            intent.putExtra("degreeMin", weather.degreeMin)
            intent.putExtra("degreeMax", weather.degreeMax)
            intent.putExtra("city", weather.city)
            intent.putExtra("status", weather.status)
            context.startActivity(intent)
        }
    }
}