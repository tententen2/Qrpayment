package com.gg.qrpayment

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.gg.qrpayment.local.AppDatabase
import com.gg.qrpayment.local.dao.HistoryPaymentDao
import com.gg.qrpayment.model.HistoryPayment
import com.gg.qrpayment.model.TestObject
import com.gg.qrpayment.model.TestObjectInner
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class HistoryDaoTest {
    lateinit var historyDao: HistoryPaymentDao
    lateinit var mDb: AppDatabase

    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getTargetContext()
        mDb = AppDatabase.getInMemoryDatabase(context)
        historyDao = mDb.historyPaymentDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        mDb.close()
    }

    @Test
    fun writeHistoryAndReadIntList() {
//        val fakeHistory = HistoryPayment("Aum", "222",
//                20.0, "0863421010", 2 ,TestObject("Test", 1), 1)
//        historyDao.insertHistoryPayment(fakeHistory)
//
//        val historyData = LiveDataTestUtil().getValue(historyDao.loadAllPayment())
//        assertThat(historyData[1], equalTo(fakeHistory))
    }

}
