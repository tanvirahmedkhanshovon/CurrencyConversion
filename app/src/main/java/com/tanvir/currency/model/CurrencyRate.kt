package com.tanvir.currency.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Entity(tableName = "currency_rate")
@Parcelize
open class CurrencyRate(@PrimaryKey val currencyCode:String, val rate:Double?) : Parcelable {

}