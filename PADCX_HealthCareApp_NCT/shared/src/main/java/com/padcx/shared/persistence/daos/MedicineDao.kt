package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.MedicineVO

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicines(medicines:List<MedicineVO>)

    @Query("SELECT * FROM medicine")
    fun getMedicines():LiveData<List<MedicineVO>>
}