package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.PatientVO

@Dao
interface PatientDao {
    @Query("SELECT * FROM patients")
    fun getPatients():LiveData<PatientVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPatients(patients:List<PatientVO>)

    @Query("SELECT * FROM patients WHERE id=:patientId")
    fun getPatientById(patientId:String):LiveData<PatientVO>

}