package com.breezedwarkamaiudyog.features.document.api

import com.breezedwarkamaiudyog.features.dymanicSection.api.DynamicApi
import com.breezedwarkamaiudyog.features.dymanicSection.api.DynamicRepo

object DocumentRepoProvider {
    fun documentRepoProvider(): DocumentRepo {
        return DocumentRepo(DocumentApi.create())
    }

    fun documentRepoProviderMultipart(): DocumentRepo {
        return DocumentRepo(DocumentApi.createImage())
    }
}