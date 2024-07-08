package com.breezedwarkamaiudyog.features.login.model.productlistmodel

import com.breezedwarkamaiudyog.app.domain.ModelEntity
import com.breezedwarkamaiudyog.app.domain.ProductListEntity
import com.breezedwarkamaiudyog.base.BaseResponse

class ModelListResponse: BaseResponse() {
    var model_list: ArrayList<ModelEntity>? = null
}