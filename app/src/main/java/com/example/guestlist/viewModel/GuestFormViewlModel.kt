package com.example.guestlist.viewModel


import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.guestlist.service.model.GuestModel
import com.example.guestlist.service.repository.Repository

//viewModel nao tem contexto e agora preciso do contexto,entao transformo em android
class GuestFormViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private val mRepository: Repository = Repository.getInstance(mContext)

    private val mPresent = MutableLiveData<Boolean>()
    var presentModel: LiveData<Boolean> = mPresent

    fun salve(name: String, presence: Boolean) {
        //meu salve precisa de tres argumentos,caso deseja omitir um,posso
        //simplesmente chamar os parâmetros,exemplo: id,name,presence.
        //chamei name e presence
        mRepository.salve(GuestModel(name = name, presence = presence))
        mPresent.value = presence

    }
}