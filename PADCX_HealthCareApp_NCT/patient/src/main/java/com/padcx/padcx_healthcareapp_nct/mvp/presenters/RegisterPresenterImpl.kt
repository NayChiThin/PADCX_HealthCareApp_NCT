package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import android.util.Log
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPresenterImpl:RegisterPresenter,AbstractBasePresenter<RegisterView>(){

    private val mRegisterModel : RegisterModel = RegisterModelImpl
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl

    override fun onTapRegister(patient: PatientVO, email: String, password: String) {
        mAuthenticationModel.register(email,password,patient.name?:"",
                onSuccess = {
                    mRegisterModel.registerNewPatient(patient,
                        onSuccess = {
                            Log.d("Firebase","Success Register Patient")
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