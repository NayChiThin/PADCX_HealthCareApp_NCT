package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.RegisterPasswordView
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPasswordPresenter:BasePresenter<RegisterPasswordView> {
    fun onUiReady()
    fun onTapConfirm(password:String,confirmPassword:String)
}