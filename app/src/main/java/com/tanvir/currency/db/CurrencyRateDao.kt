package com.tanvir.currency.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanvir.currency.model.CurrencyRate
import com.tanvir.currency.model.CurrencyRateToDisplay
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CurrencyRateDao {

   @Query("SELECT * FROM currency_rate")
   fun findAll(): Flowable<List<CurrencyRate>>
    @Query("SELECT * FROM currency_rate INNER JOIN supported_currency" +
            " ON supported_currency.currencyCode=currency_rate.currencyCode")
    fun getAllCurrencyRateWithCountryName(): Flowable<List<CurrencyRateToDisplay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<CurrencyRate>?)



}