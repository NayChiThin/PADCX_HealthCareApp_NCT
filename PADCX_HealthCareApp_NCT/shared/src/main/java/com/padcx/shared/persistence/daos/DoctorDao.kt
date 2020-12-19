package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.DoctorVO

@Dao
interface DoctorDao {

    @Query("SELECT * FROM doctors")
    fun getDoctors():LiveData<List<DoctorVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctors(doctors:List<DoctorVO>)

    @Query("SELECT * FROM doctors WHERE id=:doctorId")
    fun getDoctorById(doctorId:String):LiveData<DoctorVO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(doctor:DoctorVO)

    @Query("DELETE FROM doctors")
    fun deleteDoctors()

}