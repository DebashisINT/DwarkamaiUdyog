package com.breezedwarkamaiudyog.features.viewAllOrder.interf

import com.breezedwarkamaiudyog.app.domain.NewOrderGenderEntity
import com.breezedwarkamaiudyog.app.domain.NewOrderProductEntity

interface ProductListNewOrderOnClick {
    fun productListOnClick(product: NewOrderProductEntity)
}