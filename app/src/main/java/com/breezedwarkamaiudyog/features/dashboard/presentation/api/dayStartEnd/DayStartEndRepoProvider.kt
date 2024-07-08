package com.breezedwarkamaiudyog.features.dashboard.presentation.api.dayStartEnd

import com.breezedwarkamaiudyog.features.stockCompetetorStock.api.AddCompStockApi
import com.breezedwarkamaiudyog.features.stockCompetetorStock.api.AddCompStockRepository

object DayStartEndRepoProvider {
    fun dayStartRepositiry(): DayStartEndRepository {
        return DayStartEndRepository(DayStartEndApi.create())
    }

}