package com.padcx.doctor.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface LoginView:BaseView {
    fun navigateToHome()
    fun navigateToSignInWithFB()
    fun navigateToRegister()
}