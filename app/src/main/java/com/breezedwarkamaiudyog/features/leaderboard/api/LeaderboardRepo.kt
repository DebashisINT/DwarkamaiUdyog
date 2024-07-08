package com.breezedwarkamaiudyog.features.leaderboard.api

import android.content.Context
import android.net.Uri
import android.text.TextUtils
import com.fasterxml.jackson.databind.ObjectMapper
import com.breezedwarkamaiudyog.app.FileUtils
import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.addshop.model.AddLogReqData
import com.breezedwarkamaiudyog.features.addshop.model.AddShopRequestData
import com.breezedwarkamaiudyog.features.addshop.model.AddShopResponse
import com.breezedwarkamaiudyog.features.addshop.model.LogFileResponse
import com.breezedwarkamaiudyog.features.addshop.model.UpdateAddrReq
import com.breezedwarkamaiudyog.features.contacts.CallHisDtls
import com.breezedwarkamaiudyog.features.contacts.CompanyReqData
import com.breezedwarkamaiudyog.features.contacts.ContactMasterRes
import com.breezedwarkamaiudyog.features.contacts.SourceMasterRes
import com.breezedwarkamaiudyog.features.contacts.StageMasterRes
import com.breezedwarkamaiudyog.features.contacts.StatusMasterRes
import com.breezedwarkamaiudyog.features.contacts.TypeMasterRes
import com.breezedwarkamaiudyog.features.dashboard.presentation.DashboardActivity
import com.breezedwarkamaiudyog.features.login.model.WhatsappApiData
import com.breezedwarkamaiudyog.features.login.model.WhatsappApiFetchData
import com.google.gson.Gson
import io.reactivex.Observable
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Puja on 10-10-2024.
 */
class LeaderboardRepo(val apiService: LeaderboardApi) {

    fun branchlist(session_token: String): Observable<LeaderboardBranchData> {
        return apiService.branchList(session_token)
    }
    fun ownDatalist(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOwnData> {
        return apiService.ownDatalist(user_id,activitybased,branchwise,flag)
    }
    fun overAllAPI(user_id: String,activitybased: String,branchwise: String,flag: String): Observable<LeaderboardOverAllData> {
        return apiService.overAllDatalist(user_id,activitybased,branchwise,flag)
    }
}