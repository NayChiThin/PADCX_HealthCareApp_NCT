package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterPasswordView
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPasswordPresenter:BasePresenter<RegisterPasswordView>{
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapConfirm(password:String,confirmPassword:String)
}