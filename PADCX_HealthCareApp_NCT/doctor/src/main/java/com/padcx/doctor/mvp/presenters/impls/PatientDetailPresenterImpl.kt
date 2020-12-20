package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.padcx.doctor.mvp.presenters.PatientDetailPresenter
import com.padcx.doctor.mvp.views.PatientDetailView
import com.padcx.shared.data.models.AuthenticationModel
import com.padcx.shared.data.models.PatientDetailModel
import com.padcx.shared.data.models.impls.AuthenticationModelImpl
import com.padcx.shared.data.models.impls.PatientDetailModelImpl
import com.padcx.shared.data.vos.*
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class PatientDetailPresenterImpl:
    PatientDetailPresenter,AbstractBasePresenter<PatientDetailView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mPatientDetailModel : PatientDetailModel = PatientDetailModelImpl
    private lateinit var doctorId : String
    private lateinit var lifecycleOwner: LifecycleOwner
    private var doctor : DoctorVO = DoctorVO()
    override fun onTapStartConsult(request:ConsultRequestVO) {
        val consult = ConsultVO()
        request.status = "accepted"
        consult.doctor = doctor
        consult.patient = request.patient
        consult.caseSummary = request.caseSummary
        mPatientDetailModel.updateRequestStatus(request,
            onSuccess = {
            },
            onFailure = {
                mView.showError(it)
            })
        mPatientDetailModel.createConsultation(consult,
            onSuccess = {
                mView.navigateToChat(consult)
            },
            onFailure = {error->
                mView.showError(error)
            })
    }

    override fun onUiReady(owner: LifecycleOwner) {
        lifecycleOwner = owner
        doctorId = mAuthenticationModel.getUserId()
        mView.displayPatientDetails()

        mPatientDetailModel.getDoctorById(doctorId,
            onSuccess = {
                mPatientDetailModel.getDoctorFromDb(doctorId)
                    .observe(lifecycleOwner, Observer {
                        doctor = it
                    })
            },
            onFailure = {
                mView.showError(it)
            })
    }
}