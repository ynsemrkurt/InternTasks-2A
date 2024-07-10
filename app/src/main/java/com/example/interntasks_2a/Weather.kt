package com.example.interntasks_2a

data class Weather(
    val degree: Int,
    val degreeMin: Int,
    val degreeMax: Int,
    val city: String,
    val status: String,
)

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