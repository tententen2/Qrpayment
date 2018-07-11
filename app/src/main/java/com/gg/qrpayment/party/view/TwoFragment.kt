package com.gg.qrpayment.party.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat.getColor
import android.util.Log
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseFragment
import com.gg.qrpayment.model.PartyShare
import com.gg.qrpayment.party.viewmodel.StateValid
import com.gg.qrpayment.party.viewmodel.TwoViewModel
import com.gg.qrpayment.util.PromptPayCodeGenerator
import com.gg.qrpayment.util.QRCodeGenerator
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.android.synthetic.main.vh_custominfo.view.*

class TwoFragment : BaseFragment() {
    var data = PartyShare()
    lateinit var viewModel:TwoViewModel
    var QRcodeSize = 1000

    override fun setUp() {
        setUpViewModel()

        btnTwoPage.setOnClickListener {
            vh_input.apply {
                data.accountNumber = input_name.text.toString()
                data.money = input_amount.text.toString()
            }
            data.sumPerson = input_sum.text.toString()
            viewModel.validInput(data)
//            val perPerson = money.toDouble() / people
//            val code = PromptPayCodeGenerator(recrive, perPerson.toString()).getCode()
//            Log.d("dksoakodosa",code)
//            val qrCodeGenerator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                QRCodeGenerator(code, QRcodeSize, ContextCompat.getColor(context!!,R.color.secondaryTextColor), ContextCompat.getColor(context!!,R.color.primaryTextColor))
//            } else {
//                QRCodeGenerator(code, QRcodeSize, resources.getColor(R.color.secondaryTextColor), resources.getColor(R.color.primaryTextColor))
//            }
//            imageQr?.setImageBitmap(qrCodeGenerator.getBitmap())
//
//            val amountResult = String.format("฿ %.2f",perPerson)
//            amountResultTextView.text = amountResult

        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(activity!!).get(TwoViewModel::class.java)
        viewModel.getState().observe(this, Observer {
            when(it){

                StateValid.numberError -> { vh_input.input_layout_name.error = "number พัง" }
                StateValid.moneyError -> { vh_input.input_layout_amount.error = "money พัง" }
                StateValid.personError -> { input_layout_sum.error = "person พัง" }
            }
        })
    }
    override val layoutResourceId: Int = R.layout.fragment_two
}