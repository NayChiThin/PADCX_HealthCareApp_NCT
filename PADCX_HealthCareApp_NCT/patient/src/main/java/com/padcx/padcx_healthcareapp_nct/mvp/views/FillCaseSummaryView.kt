package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.views.BaseView

interface FillCaseSummaryView:BaseView {
    fun displayCaseSummary(generalQuestions:List<QuestionVO>,specialityQuestions:List<QuestionVO>)
    fun displayGeneralQuestion(questions: List<QuestionVO>)
    fun displaySpecialityQuestion(questions: List<QuestionVO>)
    fun displayViewPager()
    fun displayMessage(message:String)
    fun displayUserName(name:String)
    fun navigateToHome()
    fun broadcastRequest(questions: List<QuestionVO>)
}