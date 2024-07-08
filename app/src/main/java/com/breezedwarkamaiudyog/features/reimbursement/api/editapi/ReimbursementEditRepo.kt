package com.breezedwarkamaiudyog.features.reimbursement.api.editapi

import com.breezedwarkamaiudyog.base.BaseResponse
import com.breezedwarkamaiudyog.features.reimbursement.model.ApplyReimbursementInputModel
import io.reactivex.Observable

/**
 * Created by Saikat on 08-02-2019.
 */
class ReimbursementEditRepo(val apiService: ReimbursementEditApi) {
    fun editReimbursement(input: ApplyReimbursementInputModel): Observable<BaseResponse> {
        return apiService.editReimbursement(input)
    }
}