package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.QuestionVO

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getQuestions():LiveData<List<QuestionVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions:List<QuestionVO>)

    @Query("SELECT * FROM questions WHERE type='General'")
    fun getGeneralQuestions():LiveData<List<QuestionVO>>

    @Query("DELETE FROM questions")
    fun deleteQuestions()
}