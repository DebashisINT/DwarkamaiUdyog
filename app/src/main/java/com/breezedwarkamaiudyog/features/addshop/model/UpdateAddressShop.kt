package com.breezedwarkamaiudyog.features.addshop.model

import com.breezedwarkamaiudyog.app.domain.AddShopDBModelEntity
import com.breezedwarkamaiudyog.app.domain.CallHisEntity
import com.breezedwarkamaiudyog.base.BaseResponse

data class UpdateAddrReq  (var user_id:String="",var shop_list:ArrayList<UpdateAddressShop> = ArrayList())

data class UpdateAddressShop(var shop_id:String="",var shop_updated_lat:String="",var shop_updated_long:String="",var shop_updated_address:String="")