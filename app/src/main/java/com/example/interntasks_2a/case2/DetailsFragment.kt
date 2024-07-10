package com.example.interntasks_2a.case2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.interntasks_2a.Constants.CITY_KEY
import com.example.interntasks_2a.Constants.DEGREE_KEY
import com.example.interntasks_2a.Constants.STATUS_KEY
import com.example.interntasks_2a.WeatherUtils
import com.example.interntasks_2a.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val degree = it.getInt(DEGREE_KEY, 0)
            val city = it.getString(CITY_KEY)
            val status = it.getString(STATUS_KEY)

            WeatherUtils.bindWeatherData(
                binding.textViewDegree,
                binding.textViewCity,
                binding.textViewStatus,
                binding.imageViewStatus,
                degree,
                city.toString(),
                status,
                requireContext()
            )
         } ?: run {
            Log.e("Null Error", "arguments is null")
        }
    }
}