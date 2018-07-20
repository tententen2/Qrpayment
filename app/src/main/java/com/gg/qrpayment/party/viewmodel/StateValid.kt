package com.gg.qrpayment.party.viewmodel

sealed class StateValid{
    //Error
    object numberError:StateValid()
    object moneyError:StateValid()
    object personError:StateValid()

    //Success
    object numberSuccess:StateValid()
    object moneySuccess:StateValid()
    object personSuccess:StateValid()

}