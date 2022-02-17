package com.tanvir.currency.di

import android.app.Application

class CurrencyApp : Application() {
    var appServiceComponent: ApplicationComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        app = this
        appServiceComponent = DaggerApplicationComponent
            .builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        var app: CurrencyApp? = null
            private set
    }
}