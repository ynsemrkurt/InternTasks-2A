package com.example.interntasks_2a.case3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.interntasks_2a.Constants
import com.example.interntasks_2a.Weather
import com.example.interntasks_2a.WeatherAdapter
import com.example.interntasks_2a.databinding.FragmentListBinding
import com.example.interntasks_2a.weatherList

class ListFragment : Fragment() {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = WeatherAdapter(weatherList) { weather -> navigateToDetails(weather) }
        binding.recyclerViewWeather.adapter = adapter

        setFragmentResultListener(Constants.UPDATE_DEGREE_KEY) { _, result ->
            val updatedDegree = result.getInt(Constants.DEGREE_KEY)
            val weatherId = result.getInt(Constants.ID_KEY)
            updateDegree(updatedDegree, weatherId)
        }
    }

    private fun navigateToDetails(weather: Weather) {
        val action = ListFragmentDirections.actionGoDetailsFragment(weather)
        findNavController().navigate(action)
    }

    private fun updateDegree(updatedDegree: Int, weatherId: Int) {
        val updatedWeather = weatherList.find { it.id == weatherId }
        updatedWeather?.let {
            it.degree = updatedDegree
            (binding.recyclerViewWeather.adapter as? WeatherAdapter)?.notifyItemChanged(weatherList.indexOf(it))
        }
    }
}