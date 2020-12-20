package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.data.vos.ConsultRequestVO
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.mvp.views.BaseView

interface HomeView:BaseView {
    fun displaySpecialityList(specialities:List<SpecialityVO>)
    fun displayRecentDoctorList(doctors:List<DoctorVO>)
    fun displayAcceptedConsult(consultList:List<ConsultVO>)
    fun displayRequestDialog(specialityName:String)
    fun navigateToConsultation(consult:ConsultVO)
    fun navigateToAddCaseSummary(specialityName: String)
}