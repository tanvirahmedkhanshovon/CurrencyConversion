package com.tanvir.currency.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "supported_currency")
@Parcelize
class SupportedCurrency(@PrimaryKey val currencyCode:String, val countryName:String?) : Parcelable {

    override fun toString(): String {
        return currencyCode.toString()
    }
}