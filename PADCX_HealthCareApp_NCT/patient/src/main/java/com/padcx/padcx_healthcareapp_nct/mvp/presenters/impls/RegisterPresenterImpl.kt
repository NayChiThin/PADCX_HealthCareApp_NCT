package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import com.padcx.padcx_healthcareapp_nct.mvp.presenters.RegisterPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl:
    RegisterPresenter,AbstractBasePresenter<RegisterView>(){

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl

    override fun onTapNext(phone:String) {
        mAuthenticationModel.setUserPhoneNumber(phone)
        mView.navigateToRegisterPassword()
    }

    override fun onUiReady() {
        mView.displayUserName(mAuthenticationModel.getUserName())
        mView.displayUserPhoto(mAuthenticationModel.getUserPhoto())
        mView.displayPhoneNumber(mAuthenticationModel.getUserPhoneNumber())
    }
}