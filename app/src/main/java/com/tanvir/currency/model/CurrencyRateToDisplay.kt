package com.tanvir.currency.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class CurrencyRateToDisplay : Parcelable {
    var currencyCode:String?=null
    var countryName:String?=null
    var rate:Double=0.0

    override fun toString(): String {
        return currencyCode.toString()
    }
}