package com.breezedwarkamaiudyog.features.location.shopRevisitStatus

import com.breezedwarkamaiudyog.features.location.shopdurationapi.ShopDurationApi
import com.breezedwarkamaiudyog.features.location.shopdurationapi.ShopDurationRepository

object ShopRevisitStatusRepositoryProvider {
    fun provideShopRevisitStatusRepository(): ShopRevisitStatusRepository {
        return ShopRevisitStatusRepository(ShopRevisitStatusApi.create())
    }
}