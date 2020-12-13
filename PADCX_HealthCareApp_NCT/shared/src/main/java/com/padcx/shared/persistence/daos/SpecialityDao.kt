package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.MedicineVO
import com.padcx.shared.data.vos.QuestionVO
import com.padcx.shared.data.vos.SpecialityVO

@Dao
interface SpecialityDao {

    @Query("SELECT * FROM speciality")
    fun getSpecialities():LiveData<List<SpecialityVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSpecialities(specialities:List<SpecialityVO>)

    @Query("SELECT questions FROM speciality WHERE name=:name")
    fun getSpecialityQuestions(name:String):LiveData<List<QuestionVO>>

    @Query("SELECT medicine FROM speciality WHERE name=:name")
    fun getSpecialityMedicines(name:String):LiveData<List<MedicineVO>>
}