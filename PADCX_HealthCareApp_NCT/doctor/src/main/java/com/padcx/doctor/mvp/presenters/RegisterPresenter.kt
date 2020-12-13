package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.RegisterView
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface RegisterPresenter: BasePresenter<RegisterView> {
    fun onTapRegister(doctor: DoctorVO,email:String,password:String)
}