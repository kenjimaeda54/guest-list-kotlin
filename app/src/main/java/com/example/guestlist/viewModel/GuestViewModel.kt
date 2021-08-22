package com.example.guestlist.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestlist.service.constants.GuestConstants
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.Repository

class GuestViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: Repository = Repository.getInstance(application.applicationContext)


    //guestModel vai ate o repository pega  a instância do banco
    //tudo que esta no banco e setado no método getALl e passado para mGuestModel
    private val mGuestModel = MutableLiveData<List<GuestModel>>()
    var guestModel = mGuestModel


    fun load(id: Int) {
        when (id) {
            0 -> {
                mGuestModel.value = mRepository.getAll()
            }
            1 -> {
                mGuestModel.value = mRepository.getPresents()
            }
            else -> {
                mGuestModel.value = mRepository.getAbsents()
            }
        }


    }

    fun delete(id: Int) {
        val guest = mRepository.getUser(id)
        mRepository.delete(guest)
    }

}