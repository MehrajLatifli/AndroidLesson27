package com.example.androidlesson27.views.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlesson27.R
import com.example.androidlesson27.databinding.FragmentHomeBinding
import com.example.androidlesson27.utilities.gone
import com.example.androidlesson27.utilities.visible
import com.example.androidlesson27.viewmodels.HomeViewModel
import com.example.androidlesson27.views.adapters.CityAdapter
import com.example.androidlesson27.views.adapters.ResultAdapter
import com.example.androidlesson27.views.fragments.base.BaseFragment
import com.google.android.material.snackbar.Snackbar

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    private val viewModel by viewModels<HomeViewModel>()

    private val cityAdapter = CityAdapter()
    private val resultAdapter = ResultAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeData()

        val linearLayoutManager =LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.recycleViewCity.layoutManager = linearLayoutManager
        binding.recycleViewCity.adapter = cityAdapter


        val linearLayoutManager2 =LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recycleViewResult.layoutManager = linearLayoutManager2
        binding.recycleViewResult.adapter = resultAdapter

        cityAdapter.onClickItem = { city ->

            viewModel.getRealtimeWeatherbyCity(city)

        }

    }

    private fun observeData() {

        var cities = arrayListOf(
            // United Kingdom
            "London", "Manchester", "Birmingham", "Glasgow", "Liverpool",
            // United States
            "New York", "Los Angeles", "Chicago", "Houston", "Phoenix",
            // Australia
            "Sydney", "Melbourne", "Brisbane", "Perth", "Adelaide",
            // France
            "Paris", "Marseille", "Lyon", "Toulouse", "Nice",
            // Japan
            "Tokyo", "Osaka", "Nagoya", "Sapporo", "Fukuoka",
            // Germany
            "Berlin", "Hamburg", "Munich", "Cologne", "Frankfurt",
            // Canada
            "Toronto", "Montreal", "Vancouver", "Calgary", "Ottawa",
            // Italy
            "Rome", "Milan", "Naples", "Turin", "Palermo",
            // Spain
            "Madrid", "Barcelona", "Valencia", "Seville", "Bilbao",
            // China
            "Shanghai", "Beijing", "Guangzhou", "Shenzhen", "Hangzhou",
            // South Korea
            "Seoul", "Busan", "Incheon", "Daegu", "Daejeon",
            // Russia
            "Moscow", "Saint Petersburg", "Novosibirsk", "Yekaterinburg", "Kazan",
            // Brazil
            "Sao Paulo", "Rio de Janeiro", "Brasilia", "Salvador", "Fortaleza",
            // India
            "Mumbai", "Delhi", "Bangalore", "Hyderabad", "Chennai",
            // Turkey
            "Istanbul", "Ankara", "Izmir", "Bursa", "Antalya",
            // Mexico
            "Mexico City", "Guadalajara", "Monterrey", "Puebla", "Tijuana",
            // Egypt
            "Cairo", "Alexandria", "Giza", "Shubra El-Kheima", "Port Said",
            // Argentina
            "Buenos Aires", "Cordoba", "Rosario", "Mendoza", "San Miguel de Tucuman",
            // South Africa
            "Johannesburg", "Cape Town", "Durban", "Pretoria", "Port Elizabeth",
            // Indonesia
            "Jakarta", "Surabaya", "Bandung", "Medan", "Semarang",
            // Indonesia
            "Jakarta", "Surabaya", "Bandung", "Medan", "Semarang",
            //Croatia
            "Zagreb", "Split", "Rijeka", "Osijek", "Zadar",
            //Czech Republic
            "Prague", "Brno", "Ostrava", "Pilsen", "Liberec",
        )


        cityAdapter.updateList(cities)



        viewModel.weathers.observe(viewLifecycleOwner) { weathers ->
            resultAdapter.updateList(weathers)
        }

        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressBar2.visible()
            } else {
                binding.progressBar2.gone()
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {

                val snackbar = Snackbar.make(binding.root, it, Snackbar.LENGTH_LONG)
                snackbar.show()
            }
        }

    }


}