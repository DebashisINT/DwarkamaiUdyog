package com.breezedwarkamaiudyog.features.marketing.model

import com.breezedwarkamaiudyog.base.BaseResponse

/**
 * Created by Pratishruti on 05-03-2018.
 */
class GetMarketingDetailsResponse:BaseResponse() {
    lateinit var material_details:List<MarketingDetailData>
    lateinit var marketing_img:List<MarketingDetailImageData>

}