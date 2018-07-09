package com.gg.qrpayment.main.view

import android.os.Bundle
import com.gg.qrpayment.R
import com.gg.qrpayment.base.BaseActivity
import com.gg.qrpayment.extension.startNewFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity(override val layoutResourceId: Int = R.layout.activity_main) : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startNewFragment(R.id.container_main,MainFragment())
    }
}
