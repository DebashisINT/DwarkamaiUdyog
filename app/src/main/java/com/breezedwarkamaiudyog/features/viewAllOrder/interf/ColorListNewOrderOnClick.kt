package com.breezedwarkamaiudyog.features.viewAllOrder.interf

import com.breezedwarkamaiudyog.app.domain.NewOrderColorEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderProductEntity

interface ColorListNewOrderOnClick {
    fun productListOnClick(color: NewOrderColorEntity)
}