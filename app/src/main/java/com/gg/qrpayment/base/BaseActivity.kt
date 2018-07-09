package com.gg.qrpayment.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    companion object {
        const val INIT_LIST = "initlist"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
    }

    @get:LayoutRes
    protected abstract val layoutResourceId: Int
}