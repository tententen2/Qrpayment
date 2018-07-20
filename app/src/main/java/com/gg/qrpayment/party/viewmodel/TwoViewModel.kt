package com.gg.qrpayment.party.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.gg.qrpayment.local.AppDatabase
import com.gg.qrpayment.model.HistoryPayment
import com.gg.qrpayment.model.PartyShare
import com.gg.qrpayment.util.PromptPayCodeGenerator

class TwoViewModel(val mDb: AppDatabase) : ViewModel() {

    private var stateValidInput = MutableLiveData<MutableMap<StateValid, String>>()
    private var codeGenerate = MutableLiveData<String>()
    private var pricePerPerson = MutableLiveData<Double>()
    private var map: MutableMap<StateValid, String> = mutableMapOf()
    private var dataPartyShare = MutableLiveData<PartyShare>()

    fun validInput(data: PartyShare) {
        if (!data.validAccountNumber()) {
            map.remove(StateValid.numberSuccess)
            map[StateValid.numberError] = ""
        } else {
            map.remove(StateValid.numberError)
            map[StateValid.numberSuccess] = ""
        }
        if (!data.validMoneyNumber()) {
            map.remove(StateValid.moneySuccess)
            map[StateValid.moneyError] = ""
        } else {
            map.remove(StateValid.moneyError)
            map[StateValid.moneySuccess] = ""
        }
        if (!data.validsumPerson()) {
            map.remove(StateValid.personSuccess)
            map[StateValid.personError] = ""
        } else {
            map.remove(StateValid.personError)
            map[StateValid.personSuccess] = ""
        }
        stateValidInput.value = map
        stateValidInput.value?.apply {
            if (containsKey(StateValid.numberError) || containsKey(StateValid.moneyError) || containsKey(StateValid.personError)) return
        }
        generateCode(data)
        insetHistory(data)
    }

    fun generateCode(data: PartyShare) {
        val perPerson = data.money.toDouble() / data.sumPerson.toInt()
        val code = PromptPayCodeGenerator(data.accountNumber, perPerson.toString()).getCode()
        pricePerPerson.value = perPerson
        codeGenerate.value = code
    }

    fun getState(): LiveData<MutableMap<StateValid, String>> = stateValidInput

    fun getGenerateCode(): LiveData<String> = codeGenerate

    fun getPricePerPerson(): LiveData<Double> = pricePerPerson

    fun getPartyShare(): LiveData<PartyShare> = dataPartyShare

    fun setPartyPartyShare(data: PartyShare) {
        dataPartyShare.value = data
    }

    fun insetHistory(data: PartyShare) {
        mDb.let {
            val historyPayment = HistoryPayment("rerer", "222", data.money.toDouble(), data.accountNumber)
            it.historyPaymentDao().insertHistoryPayment(historyPayment)
        }
    }

    fun loadAllHistory(): LiveData<List<HistoryPayment>> = mDb.historyPaymentDao().loadAllPayment()

    class TwoViewModelFactory(val appDatabase: AppDatabase) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return TwoViewModel(mDb = appDatabase) as T
        }
    }
}