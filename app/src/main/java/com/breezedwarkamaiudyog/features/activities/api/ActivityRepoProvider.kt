package com.breezedwarkamaiudyog.features.activities.api

import com.breezedwarkamaiudyog.features.member.api.TeamApi
import com.breezedwarkamaiudyog.features.member.api.TeamRepo

object ActivityRepoProvider {
    fun activityRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.create())
    }

    fun activityImageRepoProvider(): ActivityRepo {
        return ActivityRepo(ActivityApi.createImage())
    }
}