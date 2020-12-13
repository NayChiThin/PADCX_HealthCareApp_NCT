package com.padcx.doctor.mvp.presenters

import com.padcx.doctor.mvp.views.MedicineGuideView
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.mvp.presenters.BasePresenter

interface MedicineGuidePresenter:BasePresenter<MedicineGuideView> {
    fun onTapFinishConsult(doctor:DoctorVO,patientName:String,prescription:List<PrescriptionVO>)
}