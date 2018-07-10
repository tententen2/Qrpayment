package com.gg.qrpayment.one.recycler

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.gg.qrpayment.model.Account

class OneRecycler:ListAdapter<Account,OneVHRecycler>(AccountDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OneVHRecycler {
        return OneVHRecycler.create(parent)
    }


    override fun onBindViewHolder(holder: OneVHRecycler, position: Int) {
        holder.bind()
    }

}

class AccountDiffCallback : DiffUtil.ItemCallback<Account>() {
    override fun areItemsTheSame(oldItem: Account, newItem: Account): Boolean {
        return  oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Account, newItem: Account): Boolean {
        return  oldItem == newItem
    }
}