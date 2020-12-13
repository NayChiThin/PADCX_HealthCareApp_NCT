package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.padcx.shared.data.vos.MessageVO

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(messages:List<MessageVO>)

    @Query("SELECT * FROM messages")
    fun getMessages():LiveData<List<MessageVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message:MessageVO)
}