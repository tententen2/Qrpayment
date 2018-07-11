package com.gg.qrpayment.one.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.gg.qrpayment.model.Account

class OneViewmodel:ViewModel() {
    private var data:MutableLiveData<ArrayList<Account>> = MutableLiveData()

    fun addData(name:String,amount:Double){
        var array = data.value ?: arrayListOf()
        var account = Account(array.size+1,name,amount)
        array?.add(account)
        data.value = array
    }

    fun getData():LiveData<ArrayList<Account>> = data
}