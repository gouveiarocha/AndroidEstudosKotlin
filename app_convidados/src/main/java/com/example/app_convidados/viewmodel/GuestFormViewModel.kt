package com.example.app_convidados.viewmodel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app_convidados.service.model.GuestModel
import com.example.app_convidados.service.repository.GuestRepository

class GuestFormViewModel(application: Application) : AndroidViewModel(application) {

    private val mContext = application.applicationContext
    private val mGuestRepository: GuestRepository = GuestRepository.getInstance(mContext)

    private val _saveGuest = MutableLiveData<Boolean>().apply {}
    val saveGuest = _saveGuest

    private val _guest = MutableLiveData<GuestModel>().apply {}
    val guest = _guest

    fun save(id: Int, name: String, presence: Boolean) {
        val guest = GuestModel(id, name, presence)
        if (id == 0)
            _saveGuest.value = mGuestRepository.save(guest)
        else
            _saveGuest.value = mGuestRepository.update(guest)
    }

    fun load(id: Int) {
        _guest.value = mGuestRepository.get(id)
    }

}