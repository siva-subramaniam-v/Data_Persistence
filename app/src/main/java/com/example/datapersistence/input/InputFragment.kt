package com.example.datapersistence.input

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.datapersistence.R
import com.example.datapersistence.database.MessageDatabase
import com.example.datapersistence.databinding.FragmentInputBinding

class InputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentInputBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_input, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = MessageDatabase.getInstance(application).messageDatabaseDao

        val viewModelFactory = InputViewModelFactory(dataSource, application)

        val inputViewModel =
            ViewModelProvider(this, viewModelFactory)[InputViewModel::class.java]

        binding.inputViewModel = inputViewModel

        binding.lifecycleOwner = this

        binding.saveButton.setOnClickListener {
           inputViewModel.onSaveMessage(binding.msg1InputText.text.toString(), binding.msg2InputText.text.toString())
        }

        inputViewModel.clearTextView.observe(viewLifecycleOwner) {
            binding.msg1InputText.setText("")
            binding.msg2InputText.setText("")
        }

        binding.nextButton.setOnClickListener{
            this.findNavController().navigate(
                InputFragmentDirections.actionInputFragmentToDisplayFragment()
            )
        }

        return binding.root
    }
}