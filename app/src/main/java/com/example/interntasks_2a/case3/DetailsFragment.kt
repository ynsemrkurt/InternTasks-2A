package com.example.interntasks_2a.case3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.navArgs
import com.example.interntasks_2a.Constants
import com.example.interntasks_2a.R
import com.example.interntasks_2a.Weather
import com.example.interntasks_2a.WeatherUtils
import com.example.interntasks_2a.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()
    private var updatedDegree: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val weather = args.weather

        WeatherUtils.bindWeatherData(
            binding.textViewDegree,
            binding.textViewCity,
            binding.textViewStatus,
            binding.imageViewStatus,
            weather.degree,
            weather.city,
            weather.status,
            requireContext()
        )

        binding.imageViewRefresh.setOnClickListener {
            updatedDegree = randomDegree(weather.degreeMax, weather.degreeMin)
            binding.textViewDegree.text = getString(R.string.updated_degree,updatedDegree)
        }

        binding.buttonUpdate.setOnClickListener {
            updateWeather(updatedDegree, weather)
        }
    }

    private fun randomDegree(max: Int, min: Int): Int {
        return (min..max).random()
    }

    private fun updateWeather(updatedDegree: Int?, weather: Weather) {
        updatedDegree?.let {
            val result = Bundle().apply {
                putInt(Constants.DEGREE_KEY, it)
                putInt(Constants.ID_KEY, weather.id)
            }
            setFragmentResult(Constants.UPDATE_DEGREE_KEY, result)
            parentFragmentManager.popBackStack()
        } ?: run {
            Toast.makeText(requireContext(), getString(R.string.please_refresh), Toast.LENGTH_SHORT).show()
        }
    }
}