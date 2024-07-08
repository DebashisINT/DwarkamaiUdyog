package com.breezedwarkamaiudyog.features.viewAllOrder.interf

import com.breezedwarkamaiudyog.app.domain.NewOrderGenderEntity
import com.breezedwarkamaiudyog.features.viewAllOrder.model.ProductOrder

interface ColorListOnCLick {
    fun colorListOnCLick(size_qty_list: ArrayList<ProductOrder>, adpPosition:Int)
}