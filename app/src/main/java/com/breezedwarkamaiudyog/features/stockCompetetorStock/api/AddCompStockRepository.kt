package com.breezedwarkamaiudyog.features.stockCompetetorStock.api

import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.orderList.model.NewOrderListResponseModel
import com.breezedwarkamaiudyog.features.stockCompetetorStock.ShopAddCompetetorStockRequest
import com.breezedwarkamaiudyog.features.stockCompetetorStock.model.CompetetorStockGetData
import io.reactivex.Observable

class AddCompStockRepository(val apiService:AddCompStockApi){

    fun addCompStock(shopAddCompetetorStockRequest: ShopAddCompetetorStockRequest): Observable<BaseResponse> {
        return apiService.submShopCompStock(shopAddCompetetorStockRequest)
    }

    fun getCompStockList(sessiontoken: String, user_id: String, date: String): Observable<CompetetorStockGetData> {
        return apiService.getCompStockList(sessiontoken, user_id, date)
    }
}