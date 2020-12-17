package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterView
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPresenter:BasePresenter<RegisterView> {
    fun onTapNext(phone:String)
    fun onUiReady()
}