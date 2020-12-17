package com.padcx.doctor.mvp.presenters.impls

import com.padcx.doctor.mvp.presenters.RegisterPasswordPresenter
import com.padcx.doctor.mvp.presenters.RegisterPresenter
import com.padcx.doctor.mvp.views.RegisterPasswordView
import com.padcx.doctor.mvp.views.RegisterView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPasswordPresenterImpl:RegisterPasswordPresenter,
    AbstractBasePresenter<RegisterPasswordView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    override fun onUiReady() {
        mView.displayUserName(mAuthenticationModel.getUserName())
        mView.displayUserPhoto(mAuthenticationModel.getUserPhoto())
    }

    override fun onTapConfirm(password: String, confirmPassword: String) {
        if(password.isNotEmpty()) {
            if(password == confirmPassword){
                mView.navigateToDoctorDetail()
            }else {
                mView.showError("Passwords does not match!")
            }
        }else {
            mView.showError("Password cannot be empty!")
        }
    }

}