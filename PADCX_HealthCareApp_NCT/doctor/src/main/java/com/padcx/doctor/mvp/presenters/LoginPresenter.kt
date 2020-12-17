package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.LoginView
import com.padcx.shared.mvp.presenters.BasePresenter

interface LoginPresenter:BasePresenter<LoginView> {
    fun onTapLogin(phoneNumber:String,password:String)
    fun onTapSignInWithFb()
    fun onSuccessLoginWithFb(token:String)
}