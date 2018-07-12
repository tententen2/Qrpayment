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
    private var map:MutableMap<StateValid,String> = mutableMapOf()

    fun validInput(data:PartyShare){
        if(!data.validAccountNumber()){
            map[StateValid.numberError] = ""
        }
        else{
            map.remove(StateValid.numberError)
            map[StateValid.numberSuccess] = ""
        }
        if(!data.validMoneyNumber()){
            map[StateValid.moneyError] = ""
        }else{
            map.remove(StateValid.moneyError)
            map[StateValid.moneySuccess] = ""
        }
        if(!data.validsumPerson()){
            map[StateValid.personError] = ""
        }else{
            map.remove(StateValid.personError)
            map[StateValid.personSuccess] = ""
        }
        stateValidInput.value = map
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