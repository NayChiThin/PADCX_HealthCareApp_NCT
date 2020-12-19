package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.padcx_healthcareapp_nct.mvp.views.RegisterPasswordView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.models.RegisterModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.models.impls.RegisterModelImpl
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class RegisterPasswordPresenterImpl: RegisterPasswordPresenter,AbstractBasePresenter<RegisterPasswordView>() {

    private lateinit var lifecycleOwner: LifecycleOwner
    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mRegisterModel : RegisterModel = RegisterModelImpl
    override fun onUiReady(owner: LifecycleOwner) {
        mView.displayUserName(mAuthenticationModel.getUserName())
        mView.displayUserPhoto(mAuthenticationModel.getUserPhoto())
        lifecycleOwner = owner
        mRegisterModel.getGeneralQuestions(
            onSuccess = {

            },
            onFailure = {
                mView.showError(it)
            }
        )
    }

    override fun onTapConfirm(password: String, confirmPassword: String) {
        if(password.isNotEmpty()) {
            if(password == confirmPassword){
                var questionList : MutableList<QuestionVO> = arrayListOf()
                mRegisterModel.getGeneralQuestionsFromDb()
                    .observe(lifecycleOwner, Observer {
                        questionList = it.toMutableList()
                        val patient = PatientVO()
                        patient.id = mAuthenticationModel.getUserId()
                        patient.name = mAuthenticationModel.getUserName()
                        patient.profilephoto = mAuthenticationModel.getUserPhoto()
                        patient.generalQuestions = questionList
                        mRegisterModel.registerNewPatient(patient,
                            onSuccess = {
                                mView.navigateToHome()
                            },
                            onFailure = {
                                mView.showError(it)
                            })
                    })
            }else {
                mView.showError("Passwords do not match!")
            }
        }else {
            mView.showError("Password cannot be empty!")
        }
    }
}