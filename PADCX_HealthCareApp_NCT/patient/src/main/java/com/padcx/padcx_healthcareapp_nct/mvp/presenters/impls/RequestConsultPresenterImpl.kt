package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import android.util.Log
import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RequestConsultPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.RequestConsultView
import com.padcx.shared.data.models.RequestConsultModel
import com.padcx.shared.data.models.impls.RequestConsultModelImpl
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RequestConsultPresenterImpl:
    RequestConsultPresenter,AbstractBasePresenter<RequestConsultView>() {

    private val mRequestConsultModel : RequestConsultModel =
        RequestConsultModelImpl

    override fun onTapRequestConsult(
        doctorId:String,
        consultRequest:ConsultRequestVO
    ) {
        if(doctorId=="") {
            // send request to all doctors
            mRequestConsultModel.requestConsult(consultRequest,
                onSuccess={
                    Log.d("Success","Added request to consult")
                },
                onFailure = {
                    mView.showError(it)
                })
        }else {
            // send request to specific doctor
            // to ask
        }
    }
}