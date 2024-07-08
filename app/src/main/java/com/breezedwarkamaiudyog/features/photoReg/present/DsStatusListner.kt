package com.breezedwarkamaiudyog.features.photoReg.present

import com.breezedwarkamaiudyog.app.domain.ProspectEntity
import com.breezedwarkamaiudyog.features.photoReg.model.UserListResponseModel

interface DsStatusListner {
    fun getDSInfoOnLick(obj: ProspectEntity)
}