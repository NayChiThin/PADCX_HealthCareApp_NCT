package com.padcx.shared.mvp.presenters

import com.padcx.shared.mvp.views.PatientDetailsView

class PatientDetailsPresenterImpl:PatientDetailsPresenter,AbstractBasePresenter<PatientDetailsView>() {
    override fun onTapBack() {
        mView.navigateToChat()
    }

    override fun onUiReady() {
        mView.displayDetails()
    }
}