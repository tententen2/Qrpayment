package com.gg.qrpayment.party.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Build
import android.support.design.widget.TextInputLayout
import android.support.v4.content.ContextCompat
import android.util.Log
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseFragment
import com.gg.qrpayment.local.AppDatabase
import com.gg.qrpayment.model.PartyShare
import com.gg.qrpayment.party.viewmodel.StateValid
import com.gg.qrpayment.party.viewmodel.TwoViewModel
import com.gg.qrpayment.util.QRCodeGenerator
import kotlinx.android.synthetic.main.fragment_two.*
import kotlinx.android.synthetic.main.vh_custominfo.*
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
            viewModel?.setPartyPartyShare(data)
            viewModel.validInput(data)
        }
    }

    private fun setUpViewModel() {
        val mdb = AppDatabase.getInMemoryDatabase(context!!)
        var factory: TwoViewModel.TwoViewModelFactory? = null
        mdb?.let {
            factory = TwoViewModel.TwoViewModelFactory(it)
        }
        viewModel = ViewModelProviders.of(activity!!, factory).get(TwoViewModel::class.java)
        viewModel.getState().observe(this, Observer {
            it?.let {
                it.keys.apply {
                    if (contains(StateValid.moneyError)) setError(vh_input.input_layout_amount, "money พัง")
                    if (contains(StateValid.numberError)) setError(vh_input.input_layout_name, "number พัง")
                    if (contains(StateValid.personError)) setError(input_layout_sum, "person พัง")

                    if (contains(StateValid.numberSuccess)) clearError(vh_input.input_layout_name)
                    if (contains(StateValid.moneySuccess)) clearError(vh_input.input_layout_amount)
                    if (contains(StateValid.personSuccess)) clearError(input_layout_sum)
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

        viewModel.getPartyShare().observe(this, Observer {
            vh_input.input_name.setText(it?.accountNumber)
            vh_input.input_amount.setText(it?.money)
            input_sum.setText(it?.sumPerson)
        })

        viewModel.loadAllHistory().observe(this, Observer {
            Log.d("dfdfdfdfd", it.toString())
        })

    }

    private fun setError(textView: TextInputLayout?, text: String) {
        textView?.let {
            it.apply {
                isErrorEnabled = true
                error = text
            }
        }
    }

    private fun clearError(textView: TextInputLayout?) {
        textView?.let {
            it.apply {
                error = null
                isErrorEnabled = false
            }
        }
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

    override fun onPause() {
        data.sumPerson = input_sum.text.toString()
        data.apply {
            sumPerson = input_sum.text.toString()
            accountNumber = input_name.text.toString()
            money = input_amount.text.toString()
        }
        viewModel.setPartyPartyShare(data)
        super.onPause()
    }

    override val layoutResourceId: Int = R.layout.fragment_two
}