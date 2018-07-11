package com.gg.qrpayment.one.recycler

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gg.qrpayment.R

class OneVHRecycler(var view: View):RecyclerView.ViewHolder(view) {

    companion object {
        fun create(parent:ViewGroup):OneVHRecycler{
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.vh_custominfo,parent,false)
            return OneVHRecycler(itemView)
        }
    }

    fun bind(){

    }

}