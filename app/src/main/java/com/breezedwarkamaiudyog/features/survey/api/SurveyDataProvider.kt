package com.breezedwarkamaiudyog.features.survey.api

import com.breezedwarkamaiudyog.features.photoReg.api.GetUserListPhotoRegApi
import com.breezedwarkamaiudyog.features.photoReg.api.GetUserListPhotoRegRepository

object SurveyDataProvider{

    fun provideSurveyQ(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.create())
    }

    fun provideSurveyQMultiP(): SurveyDataRepository {
        return SurveyDataRepository(SurveyDataApi.createImage())
    }
}