package com.padcx.doctor.mvp.presenters.impls

import com.padcx.doctor.mvp.presenters.CreateAccountPresenter
import com.padcx.doctor.mvp.views.CreateAccountView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class CreateAccountPresenterImpl :CreateAccountPresenter,AbstractBasePresenter<CreateAccountView>(){
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mRegisterModel : RegisterModel = RegisterModelImpl
    override fun onTapCreateAccount(doctor: DoctorVO) {
        doctor.id = mAuthenticationModel.getUserId()
        doctor.profilephoto = mAuthenticationModel.getUserPhoto()
        mRegisterModel.registerNewDoctor(doctor,
        onSuccess = {
            mView.navigateToHome()
        },
        onFailure = {
            mView.showError(it)
        })
    }

}