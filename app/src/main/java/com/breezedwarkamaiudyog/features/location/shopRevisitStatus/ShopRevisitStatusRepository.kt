package com.breezedwarkamaiudyog.features.location.shopRevisitStatus

import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.location.model.ShopDurationRequest
import com.breezedwarkamaiudyog.features.location.model.ShopRevisitStatusRequest
import io.reactivex.Observable

class ShopRevisitStatusRepository(val apiService : ShopRevisitStatusApi) {
    fun shopRevisitStatus(shopRevisitStatus: ShopRevisitStatusRequest?): Observable<BaseResponse> {
        return apiService.submShopRevisitStatus(shopRevisitStatus)
    }
}