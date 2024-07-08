package com.breezedwarkamaiudyog.features.viewAllOrder.orderOptimized

import com.breezedwarkamaiudyog.app.domain.ProductOnlineRateTempEntity
import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.login.model.productlistmodel.ProductRateDataModel
import java.io.Serializable

class ProductRateOnlineListResponseModel: BaseResponse(), Serializable {
    var product_rate_list: ArrayList<ProductOnlineRateTempEntity>? = null
}