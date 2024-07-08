package com.breezedwarkamaiudyog.fcm.api

import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.base.BaseResponse
import io.reactivex.Observable

/**
 * Created by Saikat on 27-02-2019.
 */
class UpdateDeviceTokenRepo(val apiService: UpdateDeviceTokenApi) {
    fun updateDeviceToken(deviceToken: String): Observable<BaseResponse> {
        return apiService.updateDeviceToken(Pref.user_id!!, Pref.session_token!!, deviceToken, "Android")
    }

}