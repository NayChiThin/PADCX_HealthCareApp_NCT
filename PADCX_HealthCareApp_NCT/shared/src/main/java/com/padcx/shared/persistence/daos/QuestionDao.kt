package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.QuestionVO

@Dao
interface QuestionDao {
    @Query("SELECT * FROM questions")
    fun getQuestions():LiveData<List<QuestionVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestions(questions:List<QuestionVO>)
}