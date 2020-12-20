package com.padcx.shared.mvp.views

import com.padcx.shared.data.vos.QuestionVO

interface PatientDetailsView:BaseView {
    fun displayDetails()
    fun navigateToChat()
}