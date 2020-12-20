package com.padcx.padcx_healthcareapp_nct.mvp.presenters.impls

import com.padcx.padcx_healthcareapp_nct.mvp.presenters.FillCaseSummaryPresenter
import com.padcx.padcx_healthcareapp_nct.mvp.views.FillCaseSummaryView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.FillCaseSummaryModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.FillCaseSummaryModelImpl
import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class FillCaseSummaryPresenterImpl:
    FillCaseSummaryPresenter,AbstractBasePresenter<FillCaseSummaryView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mFillCaseSummaryModel : FillCaseSummaryModel = FillCaseSummaryModelImpl
    private var patientId : String = ""

    override fun onTapContinue(questions: List<QuestionVO>) {

    }

    override fun onTapMakeRequest(generalQuestions: List<QuestionVO>,specialityQuestions:List<QuestionVO>) {
        mView.displayCaseSummary(generalQuestions,specialityQuestions)
    }

    override fun onTapConfirmDetail(questions: List<QuestionVO>,specialityName: String) {
        patientId = mAuthenticationModel.getUserId()
        for(question in questions) {
            if(!question.answer.isNullOrBlank() && question.type == "General") {
                mFillCaseSummaryModel.updateGeneralQuestionAnswer(patientId,question,
                onSuccess = {
                },
                onFailure = {
                    mView.showError(it)
                })
            }
        }
        mFillCaseSummaryModel.getPatientById(patientId,
        onSuccess = {
            val consultRequest = ConsultRequestVO()
            consultRequest.patient = it
            consultRequest.status = "pending"
            consultRequest.speciality = specialityName
            consultRequest.caseSummary = questions
            mFillCaseSummaryModel.requestConsultation(consultRequest,
            onSuccess = {
                mView.displayMessage("Successfully requested for consultation")
                mView.navigateToHome()
            },
            onFailure = {error->
                mView.showError(error)
            })
        },
        onFailure = {
            mView.showError(it)
        })
    }

    override fun onUiReady(specialityName:String) {
        // get general questions that are answered
        mView.displayUserName(mAuthenticationModel.getUserName())
        mFillCaseSummaryModel.getPatientGeneralQuestions(mAuthenticationModel.getUserId(),
        onSuccess = {
            mView.displayGeneralQuestion(it)
            mView.displayViewPager()
        },
        onFailure = {
            mView.showError(it)
        })
        mFillCaseSummaryModel.getSpecialityQuestions(specialityName,
        onSuccess = {
            mView.displaySpecialityQuestion(it)
        },
        onFailure = {
            mView.showError(it)
        })
    }
}