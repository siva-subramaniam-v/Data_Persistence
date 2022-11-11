package com.example.datapersistence.input

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.datapersistence.database.Message
import com.example.datapersistence.database.MessageDatabaseDao
import kotlinx.coroutines.*

class InputViewModel(
    val database: MessageDatabaseDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private var uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _clearTextView = MutableLiveData<Boolean>()

    val clearTextView: LiveData<Boolean>
        get() = _clearTextView

    fun onSaveMessage(message1: String, message2: String) {

        if (message1 != "" && message2 != "") {
            uiScope.launch {
                val message = Message()
                message.message1 = message1
                message.message2 = message2
                insert(message)
            }
            _clearTextView.value = true
        }
    }

    private suspend fun insert(message: Message) {
        withContext(Dispatchers.IO) {
            database.insert(message)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            database.clear()
        }
    }
}