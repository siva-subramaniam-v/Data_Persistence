package com.example.datapersistence.display

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.datapersistence.database.MessageDatabaseDao

class DisplayViewModelFactory(private val dataSources: MessageDatabaseDao): ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(DisplayViewModel::class.java)) {
            return DisplayViewModel(dataSources) as T
        }
        throw java.lang.IllegalArgumentException("unknown ViewModel class")
    }
}