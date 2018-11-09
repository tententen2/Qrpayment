package com.gg.qrpayment.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.gg.qrpayment.local.dao.HistoryPaymentDao
import com.gg.qrpayment.model.DataConverter
import com.gg.qrpayment.model.HistoryPayment

@Database(entities = arrayOf(HistoryPayment::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInMemoryDatabase(context: Context): AppDatabase {
            INSTANCE?.let {
                return it
            }?:run {
                val mdb = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                        .allowMainThreadQueries().build()
                INSTANCE = mdb
                return mdb
            }
        }
    }

    abstract fun historyPaymentDao(): HistoryPaymentDao

}