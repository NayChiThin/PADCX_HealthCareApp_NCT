package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterPasswordView
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPasswordPresenter:BasePresenter<RegisterPasswordView>{
    fun onUiReady()
    fun onTapConfirm(password:String,confirmPassword:String)
}