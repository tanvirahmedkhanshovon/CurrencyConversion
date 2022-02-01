package com.tanvir.currency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanvir.currency.model.CurrencyAppRepository
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class CurrencyViewModelFactory @Inject constructor(private var currencyAppRepository: CurrencyAppRepository?) : ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CurrencyViewModel(currencyAppRepository) as T
    }
}
