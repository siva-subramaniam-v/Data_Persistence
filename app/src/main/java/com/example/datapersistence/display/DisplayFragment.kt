package com.example.datapersistence.display

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.datapersistence.R
import com.example.datapersistence.database.MessageDatabase
import com.example.datapersistence.databinding.FragmentDisplayBinding

class DisplayFragment: Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDisplayBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_display, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MessageDatabase.getInstance(application).messageDatabaseDao

        val viewModelFactory = DisplayViewModelFactory(dataSource)

        val displayViewModel =
            ViewModelProvider(this, viewModelFactory)[DisplayViewModel::class.java]

        binding.displayViewModel = displayViewModel

        binding.lifecycleOwner = this

        return binding.root
    }
}