package com.padcx.shared.data.models.impls

import androidx.lifecycle.LiveData
import com.padcx.shared.data.models.BaseModel
import com.padcx.shared.data.models.HomeModel
import com.padcx.shared.data.vos.*
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object HomeModelImpl : HomeModel, BaseModel() {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl
    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getSpecialities(
            onSuccess = {
                mTheDB.specialityDao().deleteSpecialities()
                mTheDB.specialityDao().insertSpecialities(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun acceptRequest(
        consult: ConsultVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.createConsultation(consult, onSuccess, onFailure)
    }

    override fun getRecentDoctors(
        patientId: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getRecentDoctors(patientId,
            onSuccess = {
                mTheDB.doctorDao().deleteDoctors()
                mTheDB.doctorDao().insertDoctors(it)
                onSuccess(it)
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

    override fun getPreviousConsultations(
        doctorId: String,
        onSuccess: (List<ConsultVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultationsByDoctorId(doctorId,
            onSuccess = {
                mTheDB.consultDao().deleteConsultations()
                mTheDB.consultDao().insertConsultations(it)
//                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            })
    }

    override fun getPreviousConsultationsFromDb(doctorId: String): LiveData<List<ConsultVO>> {
        return mTheDB.consultDao().getConsultationByDoctorId(doctorId)
    }

    override fun getConsultRequest(
        onSuccess: (List<ConsultRequestVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultRequests(
            onSuccess = {
                mTheDB.consultRequestDao().deleteConsultRequests()
                mTheDB.consultRequestDao().insertConsultRequests(it)
                onSuccess(it)
            }, onFailure = {
                onFailure(it)
            })
    }

    override fun getConsultRequestFromDb(speciaityName: String): LiveData<List<ConsultRequestVO>> {
        return mTheDB.consultRequestDao().getConsultRequestsBySpeciality(speciaityName)
    }

    override fun getDoctorById(
        doctorId: String,
        onSuccess: (DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getDoctorById(doctorId, onSuccess, onFailure)
    }

    override fun getConsultation(
        onSuccess: (List<ConsultVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseApi.getConsultations(
            onSuccess={
                mTheDB.consultDao().deleteConsultations()
                mTheDB.consultDao().insertConsultations(it)
                onSuccess(it)
            },
            onFailure = {
                onFailure(it)
            }
        )
    }

    override fun getConsultByPatientIdFromDb(patientId: String): LiveData<List<ConsultVO>> {
        return mTheDB.consultDao().getConsultationByPatientId(patientId)
    }
}