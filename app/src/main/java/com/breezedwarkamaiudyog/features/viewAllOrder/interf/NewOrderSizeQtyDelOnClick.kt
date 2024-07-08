package com.breezedwarkamaiudyog.features.viewAllOrder.interf

import com.breezedwarkamaiudyog.app.domain.NewOrderGenderEntity
import com.breezedwarkamaiudyog.features.viewAllOrder.model.ProductOrder
import java.text.FieldPosition

interface NewOrderSizeQtyDelOnClick {
    fun sizeQtySelListOnClick(product_size_qty: ArrayList<ProductOrder>)
    fun sizeQtyListOnClick(product_size_qty: ProductOrder,position: Int)
}