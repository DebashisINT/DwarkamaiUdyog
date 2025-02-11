package com.breezedwarkamaiudyog.features.splash.presentation.api

import com.breezedwarkamaiudyog.features.splash.presentation.model.VersionCheckingReponseModel
import io.reactivex.Observable

/**
 * Created by Saikat on 02-01-2019.
 */
class VersionCheckingRepo(val apiService: VersionCheckingApi) {
    fun versionChecking(): Observable<VersionCheckingReponseModel> {
        return apiService.versionChecking("Android")
    }
}