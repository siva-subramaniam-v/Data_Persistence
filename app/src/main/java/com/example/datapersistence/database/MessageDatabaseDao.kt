package com.example.datapersistence.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MessageDatabaseDao {
    @Insert
    suspend fun insert(message: Message)

    @Query("SELECT * FROM message_table")
    fun getAllMessages(): LiveData<List<Message>>

    @Query("DELETE FROM message_table")
    fun clear()
}