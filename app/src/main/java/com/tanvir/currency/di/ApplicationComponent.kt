package com.tanvir.currency.di

import com.tanvir.currency.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class , CurrencyAppRepositoryModule::class])
interface ApplicationComponent {
    //abstract fun inject(homeFragment: HomeFragment)  : Unit
     fun inject(homeFragment: HomeFragment)


}