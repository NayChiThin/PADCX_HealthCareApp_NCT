package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.ConsultVO
import com.padcx.shared.data.vos.DoctorVO

@Dao
interface ConsultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultations(consultations:List<ConsultVO>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultation(consultation:ConsultVO)

    @Query("SELECT * FROM consultation")
    fun getAllConsultations():LiveData<List<ConsultVO>>

    @Query("SELECT * FROM consultation WHERE patient_id=:patientId")
    fun getConsultationByPatientId(patientId:String):LiveData<List<ConsultVO>>

    @Query("SELECT * FROM consultation WHERE id=:consultId")
    fun getConsultationById(consultId:String):LiveData<ConsultVO>

    @Query("DELETE FROM consultation")
    fun deleteConsultations()

    @Query("SELECT * FROM consultation WHERE doctor_id=:doctorId")
    fun getConsultationByDoctorId(doctorId:String):LiveData<List<ConsultVO>>
}