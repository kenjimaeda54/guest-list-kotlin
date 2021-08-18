package com.example.guestlist.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AbsentsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is absents Fragment"
    }
    val text: LiveData<String> = _text
}