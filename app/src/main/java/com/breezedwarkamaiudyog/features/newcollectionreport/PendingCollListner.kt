package com.breezedwarkamaiudyog.features.newcollectionreport

import com.breezedwarkamaiudyog.features.photoReg.model.UserListResponseModel

interface PendingCollListner {
    fun getUserInfoOnLick(obj: PendingCollData)
}