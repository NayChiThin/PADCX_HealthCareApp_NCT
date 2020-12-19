package com.padcx.padcx_healthcareapp_nct.mvp.views

import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.SpecialityVO
import com.padcx.shared.mvp.views.BaseView

interface HomeView:BaseView {
    fun displaySpecialityList(specialities:List<SpecialityVO>)
    fun displayRecentDoctorList(doctors:List<DoctorVO>)
    fun displayRequestDialog(specialityName:String)
    fun navigateToConsultation(consultId:String)
    fun navigateToAddCaseSummary(specialityName: String)
}