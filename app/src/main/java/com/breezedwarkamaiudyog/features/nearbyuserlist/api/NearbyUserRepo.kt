package com.breezedwarkamaiudyog.features.nearbyuserlist.api

import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.features.nearbyuserlist.model.NearbyUserResponseModel
import com.breezedwarkamaiudyog.features.newcollection.model.NewCollectionListResponseModel
import com.breezedwarkamaiudyog.features.newcollection.newcollectionlistapi.NewCollectionListApi
import io.reactivex.Observable

class NearbyUserRepo(val apiService: NearbyUserApi) {
    fun nearbyUserList(): Observable<NearbyUserResponseModel> {
        return apiService.getNearbyUserList(Pref.session_token!!, Pref.user_id!!)
    }
}