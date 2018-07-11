package com.gg.qrpayment.party.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gg.qrpayment.model.PartyShare

class TwoViewModel: ViewModel() {

    private var stateValidInput = MutableLiveData<StateValid>()

    fun validInput(data:PartyShare){
        if(!data.validAccountNumber())
            stateValidInput.value = StateValid.numberError
        if(!data.validMoneyNumber())
            stateValidInput.value = StateValid.moneyError
        if(!data.validsumPerson())
            stateValidInput.value = StateValid.personError
    }

    fun getState():LiveData<StateValid> = stateValidInput

}