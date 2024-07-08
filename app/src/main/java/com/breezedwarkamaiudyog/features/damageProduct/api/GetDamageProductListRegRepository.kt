package com.breezedwarkamaiudyog.features.damageProduct.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import android.util.Log
import com.breezedwarkamaiudyog.app.FileUtils
import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.NewQuotation.model.*
import com.breezedwarkamaiudyog.features.addshop.model.AddShopRequestData
import com.breezedwarkamaiudyog.features.addshop.model.AddShopResponse
import com.breezedwarkamaiudyog.features.damageProduct.model.DamageProductResponseModel
import com.breezedwarkamaiudyog.features.damageProduct.model.delBreakageReq
import com.breezedwarkamaiudyog.features.damageProduct.model.viewAllBreakageReq
import com.breezedwarkamaiudyog.features.login.model.userconfig.UserConfigResponseModel
import com.breezedwarkamaiudyog.features.myjobs.model.WIPImageSubmit
import com.breezedwarkamaiudyog.features.photoReg.model.*
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class GetDamageProductListRegRepository(val apiService : GetDamageProductListApi) {

    fun viewBreakage(req: viewAllBreakageReq): Observable<DamageProductResponseModel> {
        return apiService.viewBreakage(req)
    }

    fun delBreakage(req: delBreakageReq): Observable<BaseResponse>{
        return apiService.BreakageDel(req.user_id!!,req.breakage_number!!,req.session_token!!)
    }

}