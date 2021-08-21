package com.example.guestlist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.Repository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private var mGuestRepository: Repository =
        Repository.getInstance(application.applicationContext)

    private val mGuestModel = MutableLiveData<List<GuestModel>>()
    val guestModel: LiveData<List<GuestModel>> = mGuestModel

    fun load() {
        mGuestModel.value = mGuestRepository.getAll()

    }
}