package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.HomeView
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface HomePresenter:BasePresenter<HomeView> {
    fun onTapAcceptRequest(consult:ConsultVO)
}