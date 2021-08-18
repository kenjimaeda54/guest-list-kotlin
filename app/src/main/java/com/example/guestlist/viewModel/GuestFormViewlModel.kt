package com.example.guestlist.viewModel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.Repository

class GuestFormViewModel: ViewModel() {

     private val mRepository: Repository = Repository()

     private val mPresent = MutableLiveData<Boolean>()
     var presentModel: LiveData<Boolean> = mPresent

    fun salve(name:String,presence:Boolean) {
        mRepository.salve(GuestModel(name,presence))
        mPresent.value = presence

    }
}