package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.RequestConsultView
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface RequestConsultPresenter:BasePresenter<RequestConsultView> {
    fun onTapRequestConsult(doctorId:String,consultRequest:ConsultRequestVO)
}