package com.padcx.doctor.mvp.views

import com.padcx.shared.mvp.views.BaseView

interface RegisterPasswordView :BaseView{
    fun displayUserName(name:String)
    fun displayUserPhoto(photo:String)
    fun navigateToDoctorDetail()
}