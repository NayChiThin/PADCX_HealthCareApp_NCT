package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.LoginView
import com.padcx.shared.mvp.presenters.AbstractBasePresenter
import com.padcx.shared.mvp.presenters.BasePresenter

interface LoginPresenter:BasePresenter<LoginView> {
    fun onTapLogin(phoneNumber:String,password:String)
    fun onTapSignInWithFb()
    fun onSuccessLoginWithFb(token:String)
}