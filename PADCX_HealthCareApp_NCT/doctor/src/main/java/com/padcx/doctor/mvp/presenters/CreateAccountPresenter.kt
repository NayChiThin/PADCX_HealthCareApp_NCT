package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.CreateAccountView
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface CreateAccountPresenter:BasePresenter<CreateAccountView> {
    fun onTapCreateAccount(doctor:DoctorVO)
}