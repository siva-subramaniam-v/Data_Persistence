package com.example.datapersistence.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "message_table")
data class Message(
    @PrimaryKey(autoGenerate = true)
    var messageID: Long = 0L,

    @ColumnInfo(name = "message_one")
    var message1: String = "",

    @ColumnInfo(name = "message_two")
    var message2: String = ""
)