package com.breezedwarkamaiudyog.features.photoReg.model

import com.breezedwarkamaiudyog.features.stockAddCurrentStock.model.CurrentStockGetDataDtls

class GetUserListResponse {
    var status:String ? = null
    var message:String ? = null
    var user_list :ArrayList<UserListResponseModel>? = null
}