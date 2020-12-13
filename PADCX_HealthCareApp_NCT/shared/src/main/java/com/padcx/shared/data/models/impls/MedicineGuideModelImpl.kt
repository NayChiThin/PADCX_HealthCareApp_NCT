package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.MedicineGuideModel
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PrescriptionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object MedicineGuideModelImpl: MedicineGuideModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

    override fun finishConsult(
        doctor: DoctorVO,
        patientName:String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.addRecentDoctor(doctor,patientName,onSuccess,onFailure)
    }

    override fun savePrescription(prescription: List<PrescriptionVO>, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.savePrescription(prescription,onSuccess, onFailure)
    }
}