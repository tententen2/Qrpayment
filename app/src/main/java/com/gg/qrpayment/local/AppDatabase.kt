package com.gg.qrpayment.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.gg.qrpayment.local.dao.HistoryPaymentDao
import com.gg.qrpayment.model.HistoryPayment

@Database(entities = arrayOf(HistoryPayment::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDatabase?=null
        fun getInMemoryDatabase(context: Context): AppDatabase? {
            INSTANCE?.let {
                return INSTANCE
            }.run {
                INSTANCE = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                        .allowMainThreadQueries()
                        .build()
                return INSTANCE
            }
        }
    }

    abstract fun historyPaymentDao(): HistoryPaymentDao

}