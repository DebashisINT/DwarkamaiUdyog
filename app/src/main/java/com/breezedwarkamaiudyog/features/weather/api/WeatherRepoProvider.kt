package com.breezedwarkamaiudyog.features.weather.api

import com.breezedwarkamaiudyog.features.task.api.TaskApi
import com.breezedwarkamaiudyog.features.task.api.TaskRepo

object WeatherRepoProvider {
    fun weatherRepoProvider(): WeatherRepo {
        return WeatherRepo(WeatherApi.create())
    }
}