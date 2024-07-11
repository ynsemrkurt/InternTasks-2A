package com.example.interntasks_2a.case3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
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

        val adapter = WeatherAdapter(weatherList) { weather -> navigateToDetails(weather) }
        binding.recyclerViewWeather.adapter = adapter
    }

    private fun navigateToDetails(weather: Weather) {
        val action = ListFragmentDirections.actionGoDetailsFragment(
            weather
        )
        findNavController().navigate(action)
    }
}