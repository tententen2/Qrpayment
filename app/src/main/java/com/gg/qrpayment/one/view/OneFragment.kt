package com.gg.qrpayment.one.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseFragment
import com.gg.qrpayment.extension.random
import com.gg.qrpayment.model.Account
import com.gg.qrpayment.one.recycler.OneRecycler
import com.gg.qrpayment.one.viewmodel.OneViewmodel
import kotlinx.android.synthetic.main.fragment_one.*

class OneFragment :BaseFragment() {

    lateinit var adapter:OneRecycler
    lateinit var viewModel:OneViewmodel

    override fun setUp() {
        setUpRecycler()
        setUpViewmodel()
        setUpFab()
    }

    private fun setUpViewmodel() {
        viewModel = ViewModelProviders.of(activity!!).get(OneViewmodel::class.java)
        viewModel.getData().observe(activity!!, Observer {
            adapter.submitList(it?.distinct())
        })
    }

    private fun setUpFab() {
        fab.setOnClickListener {
            viewModel.addData("test",200.0)
        }
    }

    private fun setUpRecycler() {
        adapter = OneRecycler()
        one_recycler.layoutManager = LinearLayoutManager(context)
        one_recycler.adapter = this.adapter
    }

    override val layoutResourceId: Int = R.layout.fragment_one
}