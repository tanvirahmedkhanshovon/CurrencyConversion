package com.tanvir.currency.di

import android.app.Application


class CurrencyApp : Application() {

    private var app: CurrencyApp? = null
    private var appServiceComponent: ApplicationComponent? = null

    fun getApp(): CurrencyApp? {
        return app
    }

    fun getAppServiceComponent(): ApplicationComponent? {
        return appServiceComponent
    }

    override fun onCreate() {
        super.onCreate()
        app = this
        appServiceComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}