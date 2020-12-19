package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.padcx_healthcareapp_nct.delegates.ConsultationItemDelegate
import com.padcx.padcx_healthcareapp_nct.delegates.DoctorItemDelegate
import com.padcx.padcx_healthcareapp_nct.delegates.SpecialityItemDelegate
import com.padcx.padcx_healthcareapp_nct.mvp.views.HomeView
import com.padcx.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView>,SpecialityItemDelegate,DoctorItemDelegate,ConsultationItemDelegate{
    fun onUiReady(lifecycleOwner: LifecycleOwner)
    fun onTapRecentDoctor(doctorName:String,specialityName: String)
}