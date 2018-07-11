package com.gg.qrpayment.party.viewmodel

sealed class StateValid{
    object numberError:StateValid()
    object moneyError:StateValid()
    object personError:StateValid()
}