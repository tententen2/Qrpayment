package com.gg.qrpayment.model

class PartyShare{
    var accountNumber:String = ""
    var money:String = ""
    var sumPerson:String = ""

    fun validAccountNumber():Boolean =
            !(accountNumber.isNullOrEmpty() || accountNumber.length < 10 || accountNumber.length > 13)

    fun validMoneyNumber():Boolean = !(money.isNullOrEmpty())

    fun validsumPerson():Boolean = !(sumPerson.isNullOrEmpty())

}