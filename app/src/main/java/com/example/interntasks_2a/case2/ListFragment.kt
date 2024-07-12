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
import com.example.interntasks_2a.weatherList

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

        val adapter = WeatherAdapter(weatherList) { weather -> addBundle(weather) }
        binding.recyclerViewWeather.adapter = adapter
    }

    private fun addBundle(weather: Weather) {
        val bundle = Bundle().apply {
            putInt(DEGREE_KEY, weather.degree)
            putString(CITY_KEY, weather.city)
            putString(STATUS_KEY, weather.status)
        }
        val detailsFragment = DetailsFragment().apply {
            arguments = bundle
        }
        openFragment(detailsFragment)
    }

    private fun openFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerViewWeather, fragment)
            .addToBackStack(null)
            .commit()
    }
}