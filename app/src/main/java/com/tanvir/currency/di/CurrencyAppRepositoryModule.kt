package com.tanvir.currency.di

import android.app.Application
import com.tanvir.currency.db.SharedPref
import com.tanvir.currency.model.CurrencyAppRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CurrencyAppRepositoryModule {

    @Singleton
    @Provides
    fun providesRepository(application: Application , shared : SharedPref): CurrencyAppRepository? {
        return CurrencyAppRepository(application , shared)
    }
}