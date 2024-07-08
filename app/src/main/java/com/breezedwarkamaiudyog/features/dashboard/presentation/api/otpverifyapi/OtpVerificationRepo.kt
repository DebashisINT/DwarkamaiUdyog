package com.breezedwarkamaiudyog.features.dashboard.presentation.api.otpverifyapi

import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.base.BaseResponse
import io.reactivex.Observable

/**
 * Created by Saikat on 22-11-2018.
 */
class OtpVerificationRepo(val apiService: OtpVerificationApi) {
    fun otpVerify(shopId: String, otp: String): Observable<BaseResponse> {
        return apiService.otpVerify(Pref.session_token!!, Pref.user_id!!, shopId, otp)
    }
}