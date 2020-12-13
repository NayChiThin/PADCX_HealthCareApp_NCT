package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import com.padcx.doctor.mvp.presenters.PatientDetailPresenter
import com.padcx.doctor.mvp.views.PatientDetailView
import com.padcx.shared.data.models.PatientDetailModel
import com.padcx.shared.data.models.impls.PatientDetailModelImpl
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class PatientDetailPresenterImpl:
    PatientDetailPresenter,AbstractBasePresenter<PatientDetailView>() {

    private val mPatientDetailModel : PatientDetailModel =
        PatientDetailModelImpl
    override fun onTapStartConsult(
        consult:ConsultVO
    ) {
        // navigate to consult screen
    }
}