package com.example.datapersistence.display

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.datapersistence.database.Message
import com.example.datapersistence.database.MessageDatabaseDao
import kotlinx.coroutines.*

class DisplayViewModel(
    val database: MessageDatabaseDao
) : ViewModel() {
    private var messages = database.getAllMessages()

    var messageString = Transformations.map(messages) { messageList ->
        getMessageString(messageList)
    }

    private fun getMessageString(messages: List<Message>): String {
        val messageString = StringBuilder("")

        messages.forEach { message ->
                messageString.append("${message.messageID}. ")
                messageString.append("Label: ${message.message1}\n")
                messageString.append("Address: ${message.message2}\n\n")
        }
        Log.i("DisplayViewModel", messageString.toString())
        return messageString.toString()
    }
}