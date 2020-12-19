package com.padcx.shared.network

import com.padcx.shared.data.vos.*

interface FirebaseApi {
    fun registerNewDoctor(doctor: DoctorVO, onSuccess:()->Unit, onFailure:(String)->Unit)
    fun getSpecialities(onSuccess: (specialities:List<SpecialityVO>) -> Unit,onFailure: (String) -> Unit)
    fun getSpecialityMedicines(specialityName:String,onSuccess: (medicines:List<MedicineVO>) -> Unit,onFailure: (String) -> Unit)
    fun getSpecialityQuestions(specialityName: String,onSuccess: (questions:List<QuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun broadcastConsultRequest(consultRequest:ConsultRequestVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun addRecentDoctor(doctor: DoctorVO,patientId: String,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun createConsultation(consult:ConsultVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun saveMessage(message:MessageVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun savePrescription(prescription:List<PrescriptionVO>,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun checkout(checkout:CheckoutVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getRecentDoctors(patientId:String,onSuccess: (doctors:List<DoctorVO>) -> Unit,onFailure: (String) -> Unit)
    fun registerNewPatient(patient: PatientVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getDoctorById(doctorId:String,onSuccess: (doctor:DoctorVO) -> Unit,onFailure: (String) -> Unit)
    fun getCurrentPrescription(onSuccess: (prescription:List<PrescriptionVO>) -> Unit,onFailure: (String) -> Unit)
    fun getCurrentCaseSummary(onSuccess: (questions:List<QuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun getCurrentPatientInfo(onSuccess: (patient:PatientVO) -> Unit,onFailure: (String) -> Unit)
    fun getConsultationsByPatientId(patientId:String,onSuccess: (List<ConsultVO>) -> Unit,onFailure: (String) -> Unit)
    fun getCurrentMessages(onSuccess: (List<MessageVO>) -> Unit,onFailure: (String) -> Unit)
    fun getDoctorIdByPhoneNumber(phone:String,onSuccess: (String) -> Unit,onFailure: (String) -> Unit)
    fun getPatientById(patientId:String,onSuccess: (PatientVO) -> Unit,onFailure: (String) -> Unit)
    fun getGeneralQuestions(onSuccess: (List<QuestionVO>) -> Unit,onFailure: (String) -> Unit)
    fun updateGeneralQuestionAnswer(patientId: String,question: QuestionVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
    fun getConsultationsByDoctorId(doctorId:String,onSuccess: (List<ConsultVO>) -> Unit,onFailure: (String) -> Unit)
    fun getConsultRequests(onSuccess: (List<ConsultRequestVO>) -> Unit,onFailure: (String) -> Unit)
    fun updateRequestStatus(request:ConsultRequestVO,onSuccess: () -> Unit,onFailure: (String) -> Unit)
}