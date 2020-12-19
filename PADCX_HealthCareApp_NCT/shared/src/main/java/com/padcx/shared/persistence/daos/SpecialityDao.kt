package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.data.vos.SpecialityVO

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM speciality")
    fun getSpecialities():LiveData<List<SpecialityVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(specialities:List<SpecialityVO>)

    @Query("SELECT * FROM speciality WHERE name=:name")
    fun getSpecialityQuestions(name:String):LiveData<List<QuestionVO>>

    @Query("SELECT * FROM speciality WHERE name=:name")
    fun getSpecialityMedicines(name:String):LiveData<List<MedicineVO>>

    @Query("DELETE FROM speciality")
    fun deleteSpecialities()
}