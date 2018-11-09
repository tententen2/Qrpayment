package com.gg.qrpayment.model

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "history")
data class HistoryPayment(val name: String, val createDate: String
                          , val amount: Double, val account: String
                          , val person: Int
                          , @Embedded val testObject: TestObject
                          , @PrimaryKey(autoGenerate = true) val id: Int = 0)

data class TestObject(val text1: String, val int1: Int, @Embedded val a : TestObjectInner)

data class TestObjectInner(val a: String)

class DataConverter {

    @TypeConverter
    fun toTestObject(data: String): TestObject {
        return Gson().fromJson(data, TestObject::class.java)
    }

    @TypeConverter
    fun fromTestObject(data: TestObject): String {
        return Gson().toJson(data)
    }
}