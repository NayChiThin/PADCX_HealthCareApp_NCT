package com.padcx.shared.network

import android.util.Log
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.padcx.shared.data.vos.*
import com.padcx.shared.utils.*

object CloudFirestoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private lateinit var consultRef: DocumentReference

    override fun registerNewDoctor(
        doctor: DoctorVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_DOCTORS)
            .document(doctor.id ?: "")
            .set(doctor.toDoctorMap())
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onFailure("Please check internet connection.") }
    }

    override fun getSpecialities(
        onSuccess: (specialities: List<SpecialityVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_SPECIALITY)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val specialityList: MutableList<SpecialityVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val speciality = data.toSpecialityVO()
                            specialityList.add(speciality)
                        }
                    }
                    onSuccess(specialityList)
                }
            }
    }

    override fun getSpecialityMedicines(
        specialityName: String,
        onSuccess: (medicines: List<MedicineVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_SPECIALITY)
            .document(specialityName)
            .collection(CHILD_MEDICINE)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    val medicineList: MutableList<MedicineVO> = arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val medicine = it.toMedicineVO()
                            medicineList.add(medicine)
                        }
                    }
                    onSuccess(medicineList)
                }
            }
    }

    override fun getSpecialityQuestions(
        specialityName: String,
        onSuccess: (questions: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_SPECIALITY)
            .document(specialityName)
            .collection(CHILD_QUESTIONS)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    val questionList: MutableList<QuestionVO> = arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val question = data.toQuestionVO()
                            questionList.add(question)
                        }
                    }
                    onSuccess(questionList)
                }
            }
    }

    override fun broadcastConsultRequest(
        consultRequest: ConsultRequestVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val dbRef = db.collection(ROOT_CONSULT_REQUEST).document()
        dbRef.set(consultRequest.toConsultRequestMap())
            .addOnSuccessListener { Log.d("Success", "Added consult request") }
            .addOnFailureListener { Log.d("Failure", "Failed to add consult request") }
        for (question in consultRequest.caseSummary ?: arrayListOf()) {
            dbRef.collection(CHILD_CASE_SUMMARY)
                .document(question.name ?: "")
                .set(question.toQuestionMap())
                .addOnSuccessListener { Log.d("Success", "Added case summary") }
                .addOnFailureListener { Log.d("Failure", "Failed to add case summary") }
        }
        consultRequest.patient?.let {
            val patientRef = dbRef.collection(CHILD_PATIENT)
                .document(it.id ?: "")
            patientRef.set(it.toPatientMap())
            for (question in it.generalQuestions ?: arrayListOf()) {
                patientRef.collection(CHILD_GENERAL_QUESTIONS)
                    .document(question.name ?: "")
                    .set(question.toQuestionMap())
                    .addOnSuccessListener { Log.d("Success", "Added question") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding question") }
            }
            for (address in it.address ?: arrayListOf()) {
                patientRef.collection(CHILD_ADDRESS)
                    .document(it.id ?: "")
                    .set(address.toAddressMap())
                    .addOnSuccessListener { Log.d("Success", "Added address") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding address") }
            }
            for (doctor in it.recentDoctors ?: arrayListOf()) {
                patientRef.collection(CHILD_RECENT_DOCTORS)
                    .document(doctor.id ?: "")
                    .set(doctor.toDoctorMap())
                    .addOnSuccessListener { Log.d("Success", "Added doctor") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding doctor") }
            }
        }
    }

    override fun addRecentDoctor(
        doctor: DoctorVO,
        patientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_PATIENTS)
            .document(patientId)
            .collection(CHILD_RECENT_DOCTORS)
            .document(doctor.id ?: "")
            .set(doctor.toDoctorMap())
            .addOnSuccessListener { Log.d("Success", "Added recent doctor") }
            .addOnFailureListener { Log.d("Failure", "Failed to add recent doctor") }
    }

    override fun createConsultation(
        consult: ConsultVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        consultRef = db.collection(ROOT_CONSULTATION).document(consult.id ?: "")
        consultRef.set(consult.toConsultMap())
            .addOnSuccessListener { Log.d("Success", "Added") }
            .addOnFailureListener { Log.d("Failure", "Failed") }
        for (question in consult.caseSummary ?: arrayListOf()) {
            consultRef.collection(CHILD_CASE_SUMMARY)
                .document(question.name ?: "")
                .set(question.toQuestionMap())
                .addOnSuccessListener { Log.d("Success", "Added case summary") }
                .addOnFailureListener { Log.d("Failure", "Failed adding case summary") }
        }
        consult.doctor?.let {
            consultRef.collection(CHILD_CONSULTEES)
                .document(CHILD_DOCTOR)
                .set(it.toDoctorMap())
                .addOnSuccessListener { Log.d("Success", "Added doctor") }
                .addOnFailureListener { Log.d("Failure", "Failed adding doctor") }
        }
        consult.patient?.let {
            val patientRef = consultRef.collection(CHILD_PATIENT)
                .document(it.id ?: "")
            patientRef.set(it.toPatientMap())
            for (question in it.generalQuestions ?: arrayListOf()) {
                patientRef.collection(CHILD_GENERAL_QUESTIONS)
                    .document(question.name ?: "")
                    .set(question.toQuestionMap())
                    .addOnSuccessListener { Log.d("Success", "Added question") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding question") }
            }
            for (address in it.address ?: arrayListOf()) {
                patientRef.collection(CHILD_ADDRESS)
                    .document(it.id ?: "")
                    .set(address.toAddressMap())
                    .addOnSuccessListener { Log.d("Success", "Added address") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding address") }
            }
            for (doctor in it.recentDoctors ?: arrayListOf()) {
                patientRef.collection(CHILD_RECENT_DOCTORS)
                    .document(doctor.id ?: "")
                    .set(doctor.toDoctorMap())
                    .addOnSuccessListener { Log.d("Success", "Added doctor") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding doctor") }
            }
        }
    }

    override fun saveMessage(
        message: MessageVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        consultRef.collection(CHILD_MESSAGES)
            .document(message.id ?: "")
            .set(message.toMessageMap())
            .addOnSuccessListener { Log.d("Success", "Sent message") }
            .addOnFailureListener { Log.d("Failure", "Failed to send message") }
    }

    override fun savePrescription(
        prescription: List<PrescriptionVO>,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        for (prescript in prescription) {
            consultRef.collection(CHILD_PRESCRIPTION)
                .document(prescript.id ?: "")
                .set(prescript.toPrescriptionMap())
                .addOnSuccessListener { Log.d("Success", "Added prescription") }
                .addOnFailureListener { Log.d("Failure", "Failed adding prescription") }
        }
    }

    override fun checkout(
        checkout: CheckoutVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val ref = db.collection(ROOT_CHECKOUT).document(checkout.id ?: "")
        ref.set(checkout.toCheckoutMap())
            .addOnSuccessListener { Log.d("Success", "Checkout") }
            .addOnFailureListener { Log.d("Failure", "Cannot checkout") }
        checkout.patient?.let {
            val patientRef = ref.collection(CHILD_PATIENT)
                .document(it.id ?: "")
            patientRef.set(it.toPatientMap())
                .addOnSuccessListener { Log.d("Success", "Added") }
                .addOnFailureListener { Log.d("Failure", "Failed adding") }
            for (question in it.generalQuestions ?: arrayListOf()) {
                patientRef.collection(CHILD_GENERAL_QUESTIONS)
                    .document(question.name ?: "")
                    .set(question.toQuestionMap())
                    .addOnSuccessListener { Log.d("Success", "Added question") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding question") }
            }
            for (address in it.address ?: arrayListOf()) {
                patientRef.collection(CHILD_ADDRESS)
                    .document(it.id ?: "")
                    .set(address.toAddressMap())
                    .addOnSuccessListener { Log.d("Success", "Added address") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding address") }
            }
            for (doctor in it.recentDoctors ?: arrayListOf()) {
                patientRef.collection(CHILD_RECENT_DOCTORS)
                    .document(doctor.id ?: "")
                    .set(doctor.toDoctorMap())
                    .addOnSuccessListener { Log.d("Success", "Added doctor") }
                    .addOnFailureListener { Log.d("Failure", "Failed adding doctor") }
            }
        }
        for (prescript in checkout.prescription ?: arrayListOf()) {
            ref.collection(CHILD_PRESCRIPTION)
                .document(prescript.medicine?.name ?: "")
                .set(prescript.toPrescriptionMap())
                .addOnSuccessListener { Log.d("Success", "Added prescription") }
                .addOnFailureListener { Log.d("Failure", "Failed adding prescription") }
        }
    }

    override fun getRecentDoctors(
        patientId: String,
        onSuccess: (doctors: List<DoctorVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_PATIENTS)
            .document(patientId)
            .collection(CHILD_RECENT_DOCTORS)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    val doctorList = arrayListOf<DoctorVO>()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val doctor = data.toDoctorVO()
                            doctorList.add(doctor)
                        }
                    }
                    onSuccess(doctorList)
                }
            }

    }

    override fun registerNewPatient(
        patient: PatientVO,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        val ref = db.collection(ROOT_PATIENTS)
            .document(patient.id)
        ref.set(patient.toPatientMap())
        for (question in patient.generalQuestions ?: arrayListOf()) {
            ref.collection(CHILD_GENERAL_QUESTIONS)
                .document(question.name)
                .set(question.toQuestionMap())
                .addOnSuccessListener { Log.d("Success", "Added question") }
                .addOnFailureListener {
                    onFailure(
                        it.message ?: "Please check internet connection!"
                    )
                }
        }
        for (address in patient.address ?: arrayListOf()) {
            ref.collection(CHILD_ADDRESS)
                .document(patient.id)
                .set(address.toAddressMap())
                .addOnSuccessListener { Log.d("Success", "Added address") }
                .addOnFailureListener {
                    onFailure(
                        it.message ?: "Please check internet connection!"
                    )
                }
        }
        for (doctor in patient.recentDoctors ?: arrayListOf()) {
            ref.collection(CHILD_RECENT_DOCTORS)
                .document(doctor.id)
                .set(doctor.toDoctorMap())
                .addOnSuccessListener { Log.d("Success", "Added doctor") }
                .addOnFailureListener {
                    onFailure(
                        it.message ?: "Please check internet connection!"
                    )
                }
        }
        onSuccess()

    }

    override fun getDoctorById(
        doctorId: String,
        onSuccess: (doctor: DoctorVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_DOCTORS)
            .document(doctorId)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val data = value?.data
                    var doctor = DoctorVO()
                    data?.let {
                        doctor = data.toDoctorVO()
                        onSuccess(doctor)
                    }
                    onFailure("No doctor registered")
                }
            }
    }

    override fun getCurrentPrescription(
        onSuccess: (prescription: List<PrescriptionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        consultRef.collection(CHILD_PRESCRIPTION)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    val prescriptionList: MutableList<PrescriptionVO> = arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val prescription = data.toPrescriptionVO()
                            prescriptionList.add(prescription)
                        }
                    }
                    onSuccess(prescriptionList)
                }
            }
    }

    override fun getCurrentCaseSummary(
        onSuccess: (questions: List<QuestionVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        consultRef.collection(CHILD_CASE_SUMMARY)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    val questionList: MutableList<QuestionVO> = arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val question = data.toQuestionVO()
                            questionList.add(question)
                        }
                    }
                    onSuccess(questionList)
                }
            }
    }

    override fun getCurrentPatientInfo(
        onSuccess: (patient: PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val questionList: MutableList<QuestionVO> = arrayListOf()
        consultRef.collection(CHILD_CONSULTEES)
            .document("patient")
            .collection("general_questions")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val question = data.toQuestionVO()
                            questionList.add(question)
                        }
                    }
                }
            }
        val addressList: MutableList<AddressVO> = arrayListOf()
        consultRef.collection(CHILD_CONSULTEES)
            .document(CHILD_PATIENT)
            .collection(CHILD_ADDRESS)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val address = data.toAddressVO()
                            addressList.add(address)
                        }
                    }
                }
            }
        val doctorList: MutableList<DoctorVO> = arrayListOf()
        consultRef.collection(CHILD_CONSULTEES)
            .document(CHILD_PATIENT)
            .collection(CHILD_RECENT_DOCTORS)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        data?.let {
                            val doctor = data.toDoctorVO()
                            doctorList.add(doctor)
                        }
                    }
                }
            }
        consultRef.collection(CHILD_CONSULTEES)
            .document(CHILD_PATIENT)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val data = value?.data
                    data?.let {
                        val patient = data.toPatientVO(doctorList, questionList, addressList)
                        onSuccess(patient)
                    }
                }
            }
    }

    override fun getConsultationsByPatientId(
        patientId: String,
        onSuccess: (List<ConsultVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val rootRef = db.collection(ROOT_CONSULTATION)
        val consultationList: MutableList<ConsultVO> = arrayListOf()
        var consultId: String = ""
        rootRef
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    var doctor = DoctorVO()
                    var patient = PatientVO()
                    var messages: MutableList<MessageVO> = arrayListOf()
                    var prescriptions: MutableList<PrescriptionVO> = arrayListOf()
                    var recentDoctors: MutableList<DoctorVO> = arrayListOf()
                    var generalQuestions: MutableList<QuestionVO> = arrayListOf()
                    var addresses: MutableList<AddressVO> = arrayListOf()
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document.data
                        consultId = document.id
                        val questionList: MutableList<QuestionVO> = arrayListOf()
                        // case summary
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CASE_SUMMARY)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val question = data1.toQuestionVO()
                                            questionList.add(question)
                                        }
                                    }
                                }
                            }
                        // doctor
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CONSULTEES)
                            .document(CHILD_DOCTOR)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No connection")
                                } ?: run {
                                    val data1 = value?.data
                                    data1?.let {
                                        doctor = it.toDoctorVO()
                                    }
                                }
                            }
                        // recent doctors for patient
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CONSULTEES)
                            .document(CHILD_PATIENT)
                            .collection(CHILD_RECENT_DOCTORS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val recentDoctor = it.toDoctorVO()
                                            recentDoctors.add(recentDoctor)
                                        }
                                    }
                                }
                            }
                        // general questions for patient
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CONSULTEES)
                            .document(CHILD_PATIENT)
                            .collection(CHILD_GENERAL_QUESTIONS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "NO internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val generalQuestion = it.toQuestionVO()
                                            generalQuestions.add(generalQuestion)
                                        }
                                    }
                                }
                            }
                        // addresses for patient
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CONSULTEES)
                            .document(CHILD_PATIENT)
                            .collection(CHILD_ADDRESS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "NO internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val address = it.toAddressVO()
                                            addresses.add(address)
                                        }
                                    }
                                }
                            }
                        // patient
                        rootRef
                            .document(consultId)
                            .collection(CHILD_CONSULTEES)
                            .document(CHILD_PATIENT)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No connection")
                                } ?: run {
                                    val data1 = value?.data
                                    data1?.let {
                                        patient = it.toPatientVO(
                                            recentDoctors,
                                            generalQuestions,
                                            addresses
                                        )
                                    }
                                }
                            }
                        // messages
                        rootRef
                            .document(consultId)
                            .collection(CHILD_MESSAGES)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1?.data
                                        data1?.let {
                                            val message = it.toMessageVO()
                                            messages.add(message)
                                        }
                                    }
                                }
                            }
                        // prescription
                        rootRef
                            .document(consultId)
                            .collection(CHILD_PRESCRIPTION)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val prescription = it.toPrescriptionVO()
                                            prescriptions.add(prescription)
                                        }
                                    }
                                }
                            }

                        val consultation = ConsultVO()
                        consultation.id = data?.get("id") as String
                        consultation.patient = patient
                        consultation.caseSummary = questionList
                        consultation.prescription = prescriptions
                        consultation.messages = messages
                        consultation.doctor = doctor
                        consultationList.add(consultation)
                    }
                    onSuccess(consultationList)
                }
            }
    }

    override fun getCurrentMessages(
        onSuccess: (List<MessageVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val messages: MutableList<MessageVO> = arrayListOf()
        consultRef.collection(CHILD_MESSAGES)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document?.data
                        data?.let {
                            val message = data.toMessageVO()
                            messages.add(message)
                        }
                    }
                    onSuccess(messages)
                }
            }
    }

    override fun getDoctorIdByPhoneNumber(
        phone: String,
        onSuccess: (String) -> Unit,
        onFailure: (String) -> Unit
    ) {
        db.collection(ROOT_DOCTORS)
            .addSnapshotListener { value, error ->
                error?.let {
                    onFailure(it.message ?: "Please check internet connection")
                } ?: run {
                    val result = value?.documents ?: arrayListOf()
                    for (document in result) {
                        val data = document?.data
                        data?.let {
                            val doctor = data.toDoctorVO()
                            if (doctor.phonenumber == phone) {
                                onSuccess(doctor.id)
                            }
                        }
                    }
                }
            }
    }

    override fun getPatientById(
        patientId: String,
        onSuccess: (PatientVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val ref = db.collection(ROOT_PATIENTS)
            .document(patientId)
        ref.addSnapshotListener { value, error ->
            error?.let {
                onFailure(it.message ?: "Please check internet connection")
            } ?: run {
                var patient = PatientVO()
                var recentDoctors: MutableList<DoctorVO> = arrayListOf()
                var generalQuestions: MutableList<QuestionVO> = arrayListOf()
                var addresses: MutableList<AddressVO> = arrayListOf()
                val data = value?.data
                data?.let {
                    if (patientId == it["id"]) {
                        // recent doctors for patient
                        ref.collection(CHILD_RECENT_DOCTORS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "No connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val recentDoctor = it.toDoctorVO()
                                            recentDoctors.add(recentDoctor)
                                        }
                                    }
                                }
                            }
                        // general questions for patient
                        ref.collection(CHILD_GENERAL_QUESTIONS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "NO internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val generalQuestion = it.toQuestionVO()
                                            generalQuestions.add(generalQuestion)
                                        }
                                    }
                                }
                            }
                        // addresses for patient
                        ref.collection(CHILD_ADDRESS)
                            .addSnapshotListener { value, error ->
                                error?.let {
                                    onFailure(it.message ?: "NO internet connection")
                                } ?: run {
                                    val result1 = value?.documents ?: arrayListOf()
                                    for (document1 in result1) {
                                        val data1 = document1.data
                                        data1?.let {
                                            val address = it.toAddressVO()
                                            addresses.add(address)
                                        }
                                    }
                                }
                            }
                        // patient
                        ref.addSnapshotListener { value, error ->
                            error?.let {
                                onFailure(it.message ?: "No connection")
                            } ?: run {
                                val data1 = value?.data
                                data1?.let {patientVO->
                                    patient = patientVO.toPatientVO(recentDoctors, generalQuestions, addresses)
                                    onSuccess(patient)
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}