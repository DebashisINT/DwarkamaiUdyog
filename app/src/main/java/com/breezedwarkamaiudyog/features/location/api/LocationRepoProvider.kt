package com.breezedwarkamaiudyog.features.location.api

import com.breezedwarkamaiudyog.features.location.shopdurationapi.ShopDurationApi
import com.breezedwarkamaiudyog.features.location.shopdurationapi.ShopDurationRepository


object LocationRepoProvider {
    fun provideLocationRepository(): LocationRepo {
        return LocationRepo(LocationApi.create())
    }
}