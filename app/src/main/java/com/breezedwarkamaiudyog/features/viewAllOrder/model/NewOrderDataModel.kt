package com.breezedwarkamaiudyog.features.viewAllOrder.model

import com.breezedwarkamaiudyog.app.domain.NewOrderColorEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderGenderEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderProductEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderSizeEntity
import com.breezedwarkamaiudyog.features.stockCompetetorStock.model.CompetetorStockGetDataDtls

class NewOrderDataModel {
    var status:String ? = null
    var message:String ? = null
    var Gender_list :ArrayList<NewOrderGenderEntity>? = null
    var Product_list :ArrayList<NewOrderProductEntity>? = null
    var Color_list :ArrayList<NewOrderColorEntity>? = null
    var size_list :ArrayList<NewOrderSizeEntity>? = null
}

