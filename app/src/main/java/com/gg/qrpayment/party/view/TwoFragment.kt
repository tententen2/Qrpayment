package com.gg.qrpayment.party.view

import android.graphics.Color
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v4.content.res.ResourcesCompat.getColor
import android.util.Log
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseFragment
import com.gg.qrpayment.util.PromptPayCodeGenerator
import com.gg.qrpayment.util.QRCodeGenerator
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.android.synthetic.main.vh_custominfo.view.*

class TwoFragment : BaseFragment() {
    var recrive: String = ""
    var money: String = ""
    var people: Int = 0
    var QRcodeSize = 1000

    override fun setUp() {
        btnTwoPage.setOnClickListener {
            vh_input.apply {
                recrive = input_name.text.toString()
                money = input_amount.text.toString()
            }
            people = input_sum.text.toString().toInt()
            val perPerson = money.toDouble() / people
            val code = PromptPayCodeGenerator(recrive, perPerson.toString()).getCode()
            Log.d("dksoakodosa",code)
            val qrCodeGenerator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                QRCodeGenerator(code, QRcodeSize, ContextCompat.getColor(context!!,R.color.secondaryTextColor), ContextCompat.getColor(context!!,R.color.primaryTextColor))
            } else {
                QRCodeGenerator(code, QRcodeSize, resources.getColor(R.color.secondaryTextColor), resources.getColor(R.color.primaryTextColor))
            }
            imageQr?.setImageBitmap(qrCodeGenerator.getBitmap())
        }
    }

    override val layoutResourceId: Int = R.layout.fragment_two
}