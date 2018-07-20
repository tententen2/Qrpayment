package com.gg.qrpayment.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName="history")
data class HistoryPayment( val name: String, val createDate: String, val amount: Double, val account: String,@PrimaryKey(autoGenerate = true) val id: Int=0)