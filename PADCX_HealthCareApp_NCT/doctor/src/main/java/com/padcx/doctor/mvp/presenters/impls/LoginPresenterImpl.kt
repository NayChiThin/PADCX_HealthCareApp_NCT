package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.padcx.doctor.mvp.presenters.LoginPresenter
import com.padcx.doctor.mvp.views.LoginView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.LoginModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.LoginModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl:LoginPresenter,AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mLoginModel : LoginModel = LoginModelImpl

    override fun onTapLogin(phoneNumber: String, password: String) {
        /*if(phoneNumber.isNotEmpty()) {
            mLoginModel.getDoctorIdByPhoneNumber(phoneNumber,
            onSuccess = {
                mView.navigateToHome(it)
            },
            onFailure = {
                mView.showError(it)
            })
        }*/
    }

    override fun onTapSignInWithFb() {
        mView.navigateToSignInWithFB()
    }

    override fun onSuccessLoginWithFb(token: String) {
        mAuthenticationModel.loginWithFb(token,
            onSuccess = {
                mView.navigateToRegister()
            },
            onFailure = {
                mView.showError(it)
            })
    }
}