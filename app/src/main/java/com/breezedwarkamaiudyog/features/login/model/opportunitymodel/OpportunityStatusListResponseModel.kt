package com.breezedwarkamaiudyog.features.login.model.opportunitymodel

import com.breezedwarkamaiudyog.app.domain.OpportunityStatusEntity
import com.breezedwarkamaiudyog.app.domain.ProductListEntity
import com.breezedwarkamaiudyog.base.BaseResponse

/**
 * Created by Puja on 30.05.2024
 */
class OpportunityStatusListResponseModel : BaseResponse() {
    var status_list: ArrayList<OpportunityStatusEntity>? = null
}