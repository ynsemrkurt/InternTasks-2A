package com.example.interntasks_2a.case2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.interntasks_2a.Constants.CITY_KEY
import com.example.interntasks_2a.Constants.DEGREE_KEY
import com.example.interntasks_2a.Constants.STATUS_KEY
import com.example.interntasks_2a.R
import com.example.interntasks_2a.Weather
import com.example.interntasks_2a.WeatherAdapter
import com.example.interntasks_2a.databinding.FragmentListBinding

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
                addBundle(weatherList[position])
            }
        })
    }

    private fun addBundle(weather: Weather) {
        val bundle = Bundle()
        bundle.putInt(DEGREE_KEY, weather.degree)
        bundle.putString(CITY_KEY, weather.city)
        bundle.putString(STATUS_KEY, weather.status)
        DetailsFragment().arguments = bundle
        openFragment(DetailsFragment())
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewWeather, fragment).addToBackStack(null).commit()
    }
}