package com.gg.qrpayment.party.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gg.qrpayment.model.PartyShare
import com.gg.qrpayment.util.PromptPayCodeGenerator

class TwoViewModel: ViewModel() {

    private var stateValidInput = MutableLiveData<MutableMap<StateValid,String>>()
    private var codeGenerate = MutableLiveData<String>()
    private var pricePerPerson = MutableLiveData<Double>()

    fun validInput(data:PartyShare){
        stateValidInput.value = stateValidInput.value ?: mutableMapOf()
        if(!data.validAccountNumber()){
            stateValidInput.value?.put(StateValid.numberError,"")
        }
        else{
            stateValidInput.value?.remove(StateValid.numberError)
            stateValidInput.value?.put(StateValid.numberSuccess,"")
        }
        if(!data.validMoneyNumber()){
            stateValidInput.value?.put(StateValid.moneyError,"")
        }else{
            stateValidInput.value?.remove(StateValid.moneyError)
            stateValidInput.value?.put(StateValid.moneySuccess,"")
        }
        if(!data.validsumPerson()){
            stateValidInput.value?.put(StateValid.personError,"")
        }else{
            stateValidInput.value?.remove(StateValid.personError)
            stateValidInput.value?.put(StateValid.personSuccess,"")
        }
        stateValidInput.value?.apply {
            if(containsKey(StateValid.numberError) || containsKey(StateValid.moneyError) || containsKey(StateValid.personError)) return
        }
        generateCode(data)
    }

    fun generateCode(data: PartyShare){
        val perPerson = data.money.toDouble() / data.sumPerson.toInt()
        val code = PromptPayCodeGenerator(data.accountNumber, perPerson.toString()).getCode()
        pricePerPerson.value = perPerson
        codeGenerate.value = code
    }

    fun getState():LiveData<MutableMap<StateValid,String>> = stateValidInput

    fun getGenerateCode():LiveData<String> = codeGenerate

    fun getPricePerPerson():LiveData<Double> = pricePerPerson

}