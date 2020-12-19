package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import com.padcx.padcx_healthcareapp_nct.mvp.views.FillCaseSummaryView
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface FillCaseSummaryPresenter:BasePresenter<FillCaseSummaryView> {
    fun onTapContinue(questions:List<QuestionVO>)
    fun onTapMakeRequest(generalQuestions:List<QuestionVO>,specialityQuestions:List<QuestionVO>)
    fun onTapConfirmDetail(questions: List<QuestionVO>,specialityName: String)
    fun onUiReady(specialityName:String)
}