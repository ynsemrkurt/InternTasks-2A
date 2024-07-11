package com.example.interntasks_2a.case3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.interntasks_2a.WeatherUtils
import com.example.interntasks_2a.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val degree = args.degree
        val city = args.city
        val status = args.status

        WeatherUtils.bindWeatherData(
            binding.textViewDegree,
            binding.textViewCity,
            binding.textViewStatus,
            binding.imageViewStatus,
            degree,
            city,
            status,
            requireContext()
        )
    }
}