package com.gg.qrpayment.one.recycler

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gg.qrpayment.model.Account

class OneRecycler:RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    lateinit var list:MutableLiveData<ArrayList<Account>>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return OneVHRecycler.create(parent)
    }

    override fun getItemCount(): Int = list.value?.size ?: 0

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
    }

    fun setData(data: MutableLiveData<ArrayList<Account>>) {
        list = data
        list.observeForever {
            notifyDataSetChanged()
        }
    }

}