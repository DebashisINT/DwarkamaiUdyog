package com.breezedwarkamaiudyog.features.meetinglist.api

import com.breezedwarkamaiudyog.app.Pref
import com.breezedwarkamaiudyog.features.meetinglist.model.MeetingListResponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 21-01-2020.
 */
class MeetingRepo(val apiService: MeetingApi) {
    fun meetingList(): Observable<MeetingListResponseModel> {
        return apiService.getMeetingList(Pref.session_token!!, Pref.user_id!!)
    }
}