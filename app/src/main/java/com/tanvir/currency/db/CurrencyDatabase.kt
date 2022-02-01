package com.tanvir.currency.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tanvir.currency.model.CurrencyRate
import com.tanvir.currency.model.SupportedCurrency

@Database(
    entities = [CurrencyRate::class, SupportedCurrency::class],
    version = 1, exportSchema = false
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val countriesDao: SupportedCurrenciesDao
    abstract val currencyRateDao: CurrencyRateDao

    companion object {
        private const val DATABASE_NAME = "Currency.db"
        private val lock = Any()

        @Volatile
        private var instance: CurrencyDatabase? = null
        fun getInstance(context: Context): CurrencyDatabase? {
            if (instance == null) {
                synchronized(lock) {
                    if (instance == null) {
                        instance = Room.databaseBuilder(
                            context.applicationContext,
                            CurrencyDatabase::class.java, DATABASE_NAME
                        ).fallbackToDestructiveMigration().build()
                    }
                }
            }
            return instance
        }
    }
}