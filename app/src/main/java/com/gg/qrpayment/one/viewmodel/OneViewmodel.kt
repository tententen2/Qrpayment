package com.gg.qrpayment.one.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.gg.qrpayment.model.Account

class OneViewmodel:ViewModel() {
    private var data:MutableLiveData<ArrayList<Account>> = MutableLiveData()

    fun addData(name:String,amount:Double){
        var account = Account(name,amount)
        var array = data.value ?: arrayListOf()
        array?.add(account)
        data.value = array
    }

    fun setData(list:MutableLiveData<ArrayList<Account>>){
        data = list
    }

    fun getData():MutableLiveData<ArrayList<Account>> = data
}