package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object HomeModelImpl : HomeModel,BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialities(
            onSuccess = {
                mTheDB.specialityDao().insertSpecialities(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun acceptRequest(
            consult:ConsultVO,
            onSuccess: () -> Unit,
            onFailure: (String) -> Unit
    ) {
        mFirebaseApi.createConsultation(consult, onSuccess, onFailure)
    }

    override fun getRecentDoctors(patientId:String,onSuccess: (doctors: List<DoctorVO>) -> Unit, onFailure: (String) -> Unit) {
        mFirebaseApi.getRecentDoctors(patientId,
            onSuccess = {
                mTheDB.doctorDao().insertDoctors(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun getSpecialitiesFromDb(): LiveData<List<SpecialityVO>> {
        return mTheDB.specialityDao().getSpecialities()
    }

    override fun getRecentDoctorsFromDb(patientId: String): LiveData<List<DoctorVO>> {
        return mTheDB.doctorDao().getDoctors()
    }

}