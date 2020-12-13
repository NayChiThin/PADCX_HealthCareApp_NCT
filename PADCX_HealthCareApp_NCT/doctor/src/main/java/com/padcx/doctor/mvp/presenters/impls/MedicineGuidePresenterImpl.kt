package com.padcx.doctor.mvp.presenters.impls

import android.util.Log
import com.padcx.doctor.mvp.presenters.MedicineGuidePresenter
import com.padcx.doctor.mvp.views.MedicineGuideView
import com.padcx.shared.data.models.MedicineGuideModel
import com.padcx.shared.data.models.impls.MedicineGuideModelImpl
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.mvp.presenters.AbstractBasePresenter

class MedicineGuidePresenterImpl:
    MedicineGuidePresenter,AbstractBasePresenter<MedicineGuideView>() {

    private val mMedicineGuideModel : MedicineGuideModel = MedicineGuideModelImpl

    override fun onTapFinishConsult(doctor:DoctorVO,patientName:String,prescription:List<PrescriptionVO>) {
        // finish consult
        mMedicineGuideModel.finishConsult(doctor,patientName,
        onSuccess = {
            Log.d("Success","Finished consult and added recent doctor")
        },
        onFailure = {
            mView.showError(it)
        })
        // save prescription to api
        mMedicineGuideModel.savePrescription(prescription,
        onSuccess = {
            Log.d("Success","Finished adding prescription")
        },
        onFailure = {
            mView.showError(it)
        })
    }
}