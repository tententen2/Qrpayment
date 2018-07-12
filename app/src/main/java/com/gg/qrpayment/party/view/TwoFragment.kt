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
    lateinit var viewModel: TwoViewModel
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
        }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(activity!!).get(TwoViewModel::class.java)
        viewModel.getState().observe(this, Observer {
            it?.let {
                it.keys.apply {
                    if(contains(StateValid.numberError)) vh_input.input_layout_name.error = "number พัง"
                    if(contains(StateValid.moneyError)) vh_input.input_layout_amount.error = "money พัง"
                    if(contains(StateValid.personError)) input_layout_sum.error = "person พัง"

                    if(contains(StateValid.numberSuccess)) vh_input.input_layout_name.error = null
                    if(contains(StateValid.moneySuccess)) vh_input.input_layout_amount.error = null
                    if(contains(StateValid.personSuccess)) input_layout_sum.error = null
                }
            }
        })
        viewModel.getPricePerPerson().observe(this, Observer {
            it?.let {
                val amountResult = String.format("฿ %.2f", it)
                amountResultTextView.text = amountResult
            }
        })
        viewModel.getGenerateCode().observe(this, Observer {
            generateQRcode(it)
        })

    }

    private fun generateQRcode(it: String?) {
        it?.let {
            val qrCodeGenerator = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                QRCodeGenerator(it, QRcodeSize, ContextCompat.getColor(context!!, R.color.secondaryTextColor), ContextCompat.getColor(context!!, R.color.primaryTextColor))
            } else {
                QRCodeGenerator(it, QRcodeSize, resources.getColor(R.color.secondaryTextColor), resources.getColor(R.color.primaryTextColor))
            }
            imageQr?.setImageBitmap(qrCodeGenerator.getBitmap())

        }

    }


    override val layoutResourceId: Int = R.layout.fragment_two
}