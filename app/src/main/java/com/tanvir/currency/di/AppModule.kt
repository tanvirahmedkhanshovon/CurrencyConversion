package com.tanvir.currency.di

import android.app.Application
import com.tanvir.currency.db.SharedPref
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {
    @Singleton
    @Provides
    fun providesApplication(): Application {
        return application
    }

    @Singleton
    @Provides
    fun providesSharedPreference() : SharedPref {

        return SharedPref(application)
    }
}