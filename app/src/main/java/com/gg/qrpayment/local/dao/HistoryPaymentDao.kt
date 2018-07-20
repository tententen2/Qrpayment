package com.gg.qrpayment.local.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.gg.qrpayment.model.HistoryPayment

@Dao
interface HistoryPaymentDao {
    @Insert
    fun insertHistoryPayment(payment: HistoryPayment)

    @Query("SELECT * FROM history")
    fun loadAllPayment(): LiveData<List<HistoryPayment>>
}