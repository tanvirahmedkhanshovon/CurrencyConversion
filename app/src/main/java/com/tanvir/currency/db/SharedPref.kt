package com.tanvir.currency.db

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import javax.inject.Singleton

@Singleton
class SharedPref(context: Context) {
    private val applicationContext = context.applicationContext
    private val editor: SharedPreferences.Editor
    private val sharedPreferences: SharedPreferences = applicationContext.getSharedPreferences(
       "CurrencyPref",
       MODE_PRIVATE
   )

    init {

        editor = sharedPreferences.edit()

    }fun saveLastFetchedTime(lastSyncedTimeStamp: Long) {

        editor.putLong(LAST_SYNCED_TIME,lastSyncedTimeStamp)
         editor.commit()
    }

    fun getFetchedTime() :Long{

       return sharedPreferences.getLong(LAST_SYNCED_TIME,-1)
    }


    companion object {
        val LAST_SYNCED_TIME =  "last_synced"
    }
}