package com.breezedwarkamaiudyog.features.photoReg.adapter

import com.breezedwarkamaiudyog.features.photoReg.model.UserListResponseModel

interface PhotoAttendanceListner {
    fun getUserInfoOnLick(obj: UserListResponseModel)
    fun getUserInfoAttendReportOnLick(obj: UserListResponseModel)
}