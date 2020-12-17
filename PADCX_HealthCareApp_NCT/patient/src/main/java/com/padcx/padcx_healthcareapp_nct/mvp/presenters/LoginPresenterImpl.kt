package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import android.content.Intent
import android.util.Log
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.padcx.padcx_healthcareapp_nct.mvp.views.LoginView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.LoginModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.LoginModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl:LoginPresenter,AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mLoginModel : LoginModel = LoginModelImpl

    override fun onTapLogin(phoneNumber: String, password: String) {

    }

    override fun onTapSignInWithFb() {
        mView.navigateToSignInWithFB()
    }

    override fun onSuccessLoginWithFb(token:String) {
        mAuthenticationModel.loginWithFb(token,
            onSuccess = {
                mView.navigateToRegister()
            },
            onFailure = {
                mView.showError(it)
            })
    }
}