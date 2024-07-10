package com.example.interntasks_2a

import android.content.Context

enum class WeatherStatus(val statusResId: Int) {
    SUNNY(R.string.sunny),
    CLOUDY(R.string.cloudy),
    RAINY(R.string.rainy),
    SNOWY(R.string.snowy),
    UNKNOWN(R.string.unknown);

    companion object {
        fun fromString(context: Context, status: String): WeatherStatus {
            return entries.find { context.getString(it.statusResId) == status } ?: UNKNOWN
        }
    }
}