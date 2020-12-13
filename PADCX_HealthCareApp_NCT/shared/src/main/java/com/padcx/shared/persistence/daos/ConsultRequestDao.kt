package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.ConsultRequestVO

@Dao
interface ConsultRequestDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertConsultRequests(requests:List<ConsultRequestVO>)

    @Query("SELECT * FROM consult_requests")
    fun getAllConsultRequests():LiveData<List<ConsultRequestVO>>

    @Query("SELECT * FROM consult_requests WHERE status=:status")
    fun getConsultRequestsByStatus(status:String):LiveData<List<ConsultRequestVO>>
}