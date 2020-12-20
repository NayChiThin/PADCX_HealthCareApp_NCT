package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import com.padcx.padcx_healthcareapp_nct.mvp.presenters.LoginPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.LoginView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class LoginPresenterImpl:
    LoginPresenter,AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private var uid = ""
    override fun onUiReady() {
        uid = mAuthenticationModel.getUserId()
        if(uid.isNotEmpty() && uid!=""){
            mView.navigateToHome()
        }


    }

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