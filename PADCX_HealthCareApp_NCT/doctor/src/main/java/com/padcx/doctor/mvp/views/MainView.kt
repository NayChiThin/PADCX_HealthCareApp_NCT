package com.padcx.doctor.mvp.views

import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.views.BaseView

interface MainView:BaseView {
    fun navigateToPatientDetail(consultRequest:ConsultRequestVO)
    fun displayDoctorProfile(name:String,photo:String)
    fun displayConsultRequests(requests:List<ConsultRequestVO>)
    fun displayPreviousConsults(consults:List<ConsultVO>)
}