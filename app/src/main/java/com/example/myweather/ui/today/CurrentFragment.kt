package com.example.myweather.ui.today

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myweather.R
import com.example.myweather.data.Current
import com.example.myweather.data.source.remote.NetworkConnectionInterceptor
import com.example.myweather.data.source.remote.RequestInterceptor
import com.example.myweather.data.source.remote.WebService
import com.example.myweather.data.source.repositories.CurrentRepository
import com.example.myweather.databinding.CurrentFragmentBinding

class CurrentFragment : Fragment() {

    private var _binding: CurrentFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CurrentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val networkInterceptor = NetworkConnectionInterceptor(requireActivity())
        val requestInterceptor = RequestInterceptor()
        val webService = WebService.invoke(networkInterceptor, requestInterceptor)
        val repository = CurrentRepository(webService)
        val factory = CurrentViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CurrentViewModel::class.java)
        observeCurrent()
    }

    private fun observeCurrent() {
        viewModel.current.observe(viewLifecycleOwner, Observer {
            bindView(it)
        })
    }

    @SuppressLint("SetTextI18n")
    private fun bindView(current: Current) {
        current.apply {
            val options = RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.loading_img)
                .priority(Priority.HIGH)

            Glide.with(requireActivity()).load(weatherIcons[0]).apply(options).into(binding.ivWeather)
            binding.currentTemp.text = temperature.toString()
            binding.feelsLike.text = "$feelslike°"
            binding.tvHumidity.text = "$humidity%"
            binding.tvPressure.text = "$pressure mBar"
            binding.tvUvIndex.text = "$uvIndex"
            binding.tvVisibility.text = "$visibility km"
            binding.tvPrecipitation.text = "$precip %"
            binding.tvDescription.text = weatherDescriptions[0]
            binding.tvWindSpeed.text = "$windSpeed km/h"
            binding.tvWindDirection.text = windDir
            binding.tvWindDegree.text = windDegree.toString()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}