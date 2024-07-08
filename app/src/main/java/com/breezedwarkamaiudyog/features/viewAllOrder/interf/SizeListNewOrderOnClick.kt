package com.breezedwarkamaiudyog.features.viewAllOrder.interf

import com.breezedwarkamaiudyog.app.domain.NewOrderProductEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderSizeEntity

interface SizeListNewOrderOnClick {
    fun sizeListOnClick(size: NewOrderSizeEntity)
}