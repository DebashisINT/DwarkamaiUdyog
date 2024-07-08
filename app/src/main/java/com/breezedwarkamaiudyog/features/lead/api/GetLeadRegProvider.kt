package com.breezedwarkamaiudyog.features.lead.api

import com.breezedwarkamaiudyog.features.NewQuotation.api.GetQuotListRegRepository
import com.breezedwarkamaiudyog.features.NewQuotation.api.GetQutoListApi


object GetLeadRegProvider {
    fun provideList(): GetLeadListRegRepository {
        return GetLeadListRegRepository(GetLeadListApi.create())
    }
}