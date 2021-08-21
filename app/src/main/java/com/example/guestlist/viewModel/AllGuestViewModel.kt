package com.example.guestlist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.Repository

class AllGuestViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: Repository = Repository.getInstance(application)


    //guestModel vai ate o repository pega  a instância do banco
    //tudo que esta no banco e setado no método getALl e passado para mGuestModel
    private val mGuestModel = MutableLiveData<List<GuestModel>>()
    var guestModel = mGuestModel


    fun load() {
        mGuestModel.value = mRepository.getAll()

    }

}