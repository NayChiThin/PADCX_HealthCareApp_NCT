package com.padcx.shared.mvp.presenters

import com.padcx.shared.mvp.views.PatientDetailsView

interface PatientDetailsPresenter:BasePresenter<PatientDetailsView> {
    fun onTapBack()
    fun onUiReady()
}