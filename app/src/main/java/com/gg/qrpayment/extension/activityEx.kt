package com.gg.qrpayment.extension

import android.app.Activity
import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity

internal inline fun <reified T : Activity> AppCompatActivity.startNewPage(intent2:Intent.() -> Unit = {}) {
    var intent = Intent(this, T::class.java)
    intent.intent2()
    this.startActivity(intent)
}

internal inline fun AppCompatActivity.startNewFragment(container:Int,fragment:android.support.v4.app.Fragment){
    this.supportFragmentManager.beginTransaction().replace(container,fragment).commitAllowingStateLoss()
}

