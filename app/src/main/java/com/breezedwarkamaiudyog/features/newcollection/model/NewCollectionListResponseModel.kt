package com.breezedwarkamaiudyog.features.newcollection.model

import com.breezedwarkamaiudyog.app.domain.CollectionDetailsEntity
import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.shopdetail.presentation.model.collectionlist.CollectionListDataModel

/**
 * Created by Saikat on 15-02-2019.
 */
class NewCollectionListResponseModel : BaseResponse() {
    //var collection_list: ArrayList<CollectionListDataModel>? = null
    var collection_list: ArrayList<CollectionDetailsEntity>? = null
}