package com.breezedwarkamaiudyog.features.stockAddCurrentStock.api

import com.breezedwarkamaiudyog.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezedwarkamaiudyog.features.location.shopRevisitStatus.ShopRevisitStatusRepository

object ShopAddStockProvider {
    fun provideShopAddStockRepository(): ShopAddStockRepository {
        return ShopAddStockRepository(ShopAddStockApi.create())
    }
}