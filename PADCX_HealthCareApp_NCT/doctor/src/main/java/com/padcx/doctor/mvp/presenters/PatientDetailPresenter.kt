package com.padcx.doctor.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padcx.doctor.mvp.views.PatientDetailView
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.BasePresenter

interface PatientDetailPresenter:BasePresenter<PatientDetailView> {
    fun onTapStartConsult(request:ConsultRequestVO)
    fun onUiReady(lifecycleOwner: LifecycleOwner)
}