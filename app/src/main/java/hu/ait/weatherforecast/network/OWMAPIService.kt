package hu.ait.weatherforecast.network

import retrofit2.Retrofit

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())