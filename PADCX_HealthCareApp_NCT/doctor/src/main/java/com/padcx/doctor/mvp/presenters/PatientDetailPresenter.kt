package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.PatientDetailView
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface PatientDetailPresenter:BasePresenter<PatientDetailView> {
    fun onTapStartConsult(consult:ConsultVO)
}