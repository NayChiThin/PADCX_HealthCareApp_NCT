package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.MedicineVO

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMedicines(medicines:List<MedicineVO>)

    @Query("SELECT * FROM medicine")
    fun getMedicines():LiveData<List<MedicineVO>>

    @Query("DELETE FROM medicine")
    fun deleteMedicines()
}