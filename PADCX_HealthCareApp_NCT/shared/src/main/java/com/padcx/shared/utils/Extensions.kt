package com.padcx.shared.utils

import com.padcx.shared.data.vos.*

fun MutableMap<String,Any>.toQuestionVO() : QuestionVO {
    val question = QuestionVO()
    question.name = this["name"] as String
    question.answer = this["answer"] as String
    question.sentence = this["sentence"] as String
    question.ask_time = this["ask_time"] as String
    question.type = this["type"] as String
    return question
}
fun MutableMap<String,Any>.toAddressVO() : AddressVO {
    val address = AddressVO()
    address.id = this["id"] as String
    address.address = this["address"] as String
    return address
}
fun MutableMap<String,Any>.toDeliverRoutineVO() : DeliverRoutineVO {
    val deliverRoutine = DeliverRoutineVO()
    deliverRoutine.date = this["date"] as String
    deliverRoutine.time = this["time"] as String
    return deliverRoutine
}
fun MutableMap<String,Any>.toDoctorVO():DoctorVO {
    val doctor = DoctorVO()
    doctor.id = this["id"] as String
    doctor.name = this["name"] as String
    doctor.certificate = this["certificate"] as String
    doctor.description = this["description"] as String
    doctor.phonenumber = this["phonenumber"] as String
    doctor.profilephoto = this["profilephoto"] as String
    doctor.speciality = this["speciality"] as String
    doctor.address = this["address"] as String
    doctor.gender = this["gender"] as String
    doctor.dob = this["dob"] as String
    doctor.experience = this["experience"] as String
    return doctor
}
fun MutableMap<String,Any>.toMedicineVO() : MedicineVO {
    val medicine = MedicineVO()
    medicine.name = this["name"] as String
    medicine.cost = this["cost"] as Float
    return medicine
}
fun MutableMap<String,Any>.toMessageVO() : MessageVO {
    val message = MessageVO()
    message.id = this["id"] as String
    message.text = this["text"] as String
    message.date = this["date"] as String
    message.time = this["time"] as String
    message.image = this["image"] as String
    message.sender = this["sender"] as String
    return message
}
fun MutableMap<String,Any>.toPrescriptionVO():PrescriptionVO {
    val prescription = PrescriptionVO()
    prescription.id = this["id"] as String
    prescription.count = this["count"] as Int
    val routine : Map<String,Any> = this["routine"] as Map<String,Any>
    prescription.routine = routine.toMutableMap().toRoutineVO()
    val medicine : Map<String,Any> = this["medicine"] as Map<String, Any>
    prescription.medicine = medicine.toMutableMap().toMedicineVO()
    return prescription
}
fun MutableMap<String,Any>.toRoutineVO():RoutineVO {
    val routine = RoutineVO()
    routine.day = this["day"] as String
    routine.times_per_day = this["times_per_day"] as Int
    routine.time = this["time"] as String
    routine.total_days = this["total_days"] as Int
    return routine
}
fun MutableMap<String,Any>.toSpecialityVO():SpecialityVO {
    val speciality = SpecialityVO()
    speciality.name = this["name"] as String
    return speciality
}
fun MutableMap<String,Any>.toPatientVO(
    doctors:MutableList<DoctorVO>,
    questions:MutableList<QuestionVO>,
    addresses:MutableList<AddressVO>):PatientVO{
    val patient = PatientVO()
    patient.id = this["id"] as String
    patient.name = this["name"] as String
    patient.profilephoto = this["profilephoto"] as String
    patient.address = addresses
    patient.recentDoctors = doctors
    patient.generalQuestions = questions
    return patient
}