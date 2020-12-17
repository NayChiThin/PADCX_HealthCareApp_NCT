package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface LoginView: BaseView {
    fun navigateToHome()
    fun navigateToSignInWithFB()
    fun navigateToRegister()
}