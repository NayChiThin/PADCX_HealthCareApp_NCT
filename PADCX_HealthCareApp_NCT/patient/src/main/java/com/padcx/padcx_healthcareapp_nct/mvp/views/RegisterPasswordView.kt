package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface RegisterPasswordView:BaseView {
    fun displayUserName(name:String)
    fun displayUserPhoto(photo:String)
    fun navigateToHome()
}