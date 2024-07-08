package com.breezedwarkamaiudyog.features.stockAddCurrentStock.api

import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.location.model.ShopRevisitStatusRequest
import com.breezedwarkamaiudyog.features.location.shopRevisitStatus.ShopRevisitStatusApi
import com.breezedwarkamaiudyog.features.stockAddCurrentStock.ShopAddCurrentStockRequest
import com.breezedwarkamaiudyog.features.stockAddCurrentStock.model.CurrentStockGetData
import com.breezedwarkamaiudyog.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class ShopAddStockRepository (val apiService : ShopAddStockApi){
    fun shopAddStock(shopAddCurrentStockRequest: ShopAddCurrentStockRequest?): Observable<BaseResponse> {
        return apiService.submShopAddStock(shopAddCurrentStockRequest)
    }

    fun getCurrStockList(sessiontoken: String, user_id: String, date: String): Observable<CurrentStockGetData> {
        return apiService.getCurrStockListApi(sessiontoken, user_id, date)
    }

}