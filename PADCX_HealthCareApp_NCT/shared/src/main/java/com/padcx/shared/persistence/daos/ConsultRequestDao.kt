package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.ConsultRequestVO

@Dao
interface ConsultRequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultRequests(requests:List<ConsultRequestVO>)

    @Query("SELECT * FROM consult_request")
    fun getAllConsultRequests():LiveData<List<ConsultRequestVO>>

    @Query("SELECT * FROM consult_request WHERE speciality=:speciality AND status='pending'")
    fun getConsultRequestsBySpeciality(speciality:String):LiveData<List<ConsultRequestVO>>

    @Query("DELETE FROM consult_request")
    fun deleteConsultRequests()
}