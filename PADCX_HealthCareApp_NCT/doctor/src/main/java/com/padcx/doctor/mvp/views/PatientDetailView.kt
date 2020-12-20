package com.padcx.doctor.mvp.views

import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.views.BaseView

interface PatientDetailView:BaseView {
    fun displayPatientDetails()
    fun navigateToChat(consult: ConsultVO)
}