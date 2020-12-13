package com.padcx.shared.data.models.impls

import com.padcx.shared.data.models.PatientDetailModel
import com.padcx.shared.data.vos.DoctorVO
import com.padcx.shared.data.vos.PatientVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.network.CloudFirestoreFirebaseApiImpl
import com.padcx.shared.network.FirebaseApi

object PatientDetailModelImpl: PatientDetailModel {
    override var mFirebaseApi: FirebaseApi = CloudFirestoreFirebaseApiImpl

}