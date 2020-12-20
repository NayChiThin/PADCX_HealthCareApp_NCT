package com.padcx.doctor.mvp.views

import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.MessageVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.views.BaseView

interface ChatView:BaseView {
    fun displayMessages(messages:List<MessageVO>)
    fun displayPatientDetails(questions:List<QuestionVO>)
    fun displaySpecialityDetails(questions: List<QuestionVO>)
    fun setUpRecyclerView(userName:String)
    fun navigateToHome()
    fun displayLatestPositionScrollView()
    fun navigateToPatientDetails()
}