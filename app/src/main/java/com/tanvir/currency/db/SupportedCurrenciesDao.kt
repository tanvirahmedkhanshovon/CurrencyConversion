package com.tanvir.currency.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.tanvir.currency.model.SupportedCurrency
import io.reactivex.rxjava3.core.Flowable

@Dao
interface SupportedCurrenciesDao {

    @Query("SELECT * FROM supported_currency")
    fun findAll(): Flowable<List<SupportedCurrency>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(users: List<SupportedCurrency>?)

}