package com.padcx.doctor.mvp.presenters.impls

import com.padcx.doctor.mvp.presenters.HomePresenter
import com.padcx.doctor.mvp.views.HomeView
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.models.impls.HomeModelImpl
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class HomePresenterImpl: HomePresenter,AbstractBasePresenter<HomeView>() {
    private val mHomeModel : HomeModel = HomeModelImpl
    override fun onTapAcceptRequest(consult:ConsultVO) {
        mHomeModel.acceptRequest(consult,
        onSuccess = {
            // created consultation
        },
        onFailure = {
            mView.showError(it)
        })
    }
}