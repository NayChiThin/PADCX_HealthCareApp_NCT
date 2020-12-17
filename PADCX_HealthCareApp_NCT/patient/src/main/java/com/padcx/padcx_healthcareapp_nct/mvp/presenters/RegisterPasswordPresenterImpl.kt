package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterPasswordView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPasswordPresenterImpl: RegisterPasswordPresenter,AbstractBasePresenter<RegisterPasswordView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mRegisterModel : RegisterModel = RegisterModelImpl
    override fun onUiReady() {
        mView.displayUserName(mAuthenticationModel.getUserName())
        mView.displayUserPhoto(mAuthenticationModel.getUserPhoto())
    }

    override fun onTapConfirm(password: String, confirmPassword: String) {
        if(password.isNotEmpty()) {
            if(password == confirmPassword){
                val patient = PatientVO()
                patient.id = mAuthenticationModel.getUserId()
                patient.name = mAuthenticationModel.getUserName()
                patient.profilephoto = mAuthenticationModel.getUserPhoto()
                mRegisterModel.registerNewPatient(patient,
                    onSuccess = {
                        mView.navigateToHome()
                    },
                    onFailure = {
                        mView.showError(it)
                    })
            }else {
                mView.showError("Passwords does not match!")
            }
        }else {
            mView.showError("Password cannot be empty!")
        }
    }
}