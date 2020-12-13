package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import com.padcx.doctor.mvp.presenters.RegisterPresenter
import com.padcx.doctor.mvp.views.RegisterView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl : RegisterPresenter, AbstractBasePresenter<RegisterView>() {

    private val mRegisterModel : RegisterModel = RegisterModelImpl
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(
        doctor: DoctorVO,
        email:String,
        password:String
    ) {
        mAuthenticationModel.register(email,password,doctor.name?:"",
        onSuccess = {
            Log.d("Firebase","Success Register Authentication")
            mRegisterModel.registerNewDoctor(doctor,
                onSuccess = {
                    Log.d("Firebase","Success Register Doctor")
                },
                onFailure = {
                    mView.showError(it)
                })
        },
        onFailure = {
            mView.showError(it)
        })
    }
}