package com.padcx.padcx_healthcareapp_nct.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.padcx_healthcareapp_nct.mvp.views.HomeView
import com.padcx.shared.mvp.presenters.BasePresenter

interface HomePresenter : BasePresenter<HomeView>{
    fun onUiReady(lifecycleOwner: LifecycleOwner,patientId:String)
    fun onTapStartConsult()
    fun onTapRecentDoctor(doctorName:String,specialityName: String)
    fun onTapSpeciality(specialityName:String)
}