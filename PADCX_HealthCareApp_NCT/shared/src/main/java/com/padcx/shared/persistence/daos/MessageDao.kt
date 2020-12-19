package com.padcx.shared.persistence.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.padcx.shared.data.vos.MessageVO

@Dao
interface MessageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessages(messages:List<MessageVO>)

    @Query("SELECT * FROM messages")
    fun getMessages():LiveData<List<MessageVO>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMessage(message:MessageVO)

    @Query("DELETE FROM messages")
    fun deleteMessages()
}