package com.breezedwarkamaiudyog.features.weather.api

import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.task.api.TaskApi
import com.breezedwarkamaiudyog.features.task.model.AddTaskInputModel
import com.breezedwarkamaiudyog.features.weather.model.ForeCastAPIResponse
import com.breezedwarkamaiudyog.features.weather.model.WeatherAPIResponse
import io.reactivex.Observable

class WeatherRepo(val apiService: WeatherApi) {
    fun getCurrentWeather(zipCode: String): Observable<WeatherAPIResponse> {
        return apiService.getTodayWeather(zipCode)
    }

    fun getWeatherForecast(zipCode: String): Observable<ForeCastAPIResponse> {
        return apiService.getForecast(zipCode)
    }
}