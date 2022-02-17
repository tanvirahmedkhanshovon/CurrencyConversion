package com.tanvir.currency.viewmodel

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tanvir.currency.model.CurrencyAppRepository
import com.tanvir.currency.model.CurrencyRateToDisplay
import com.tanvir.currency.model.SupportedCurrency

class CurrencyViewModel constructor(private var currencyAppRepository: CurrencyAppRepository?) :
    ViewModel() {

    val showLoading = ObservableBoolean()
    var countriesList: MutableLiveData<List<SupportedCurrency>?>? = null
    var currencyRateList: MutableLiveData<List<CurrencyRateToDisplay>?>? = null

    var mCurrentMultiplier = ObservableField<String>("1.0")
    var mCurrentCurrency = MutableLiveData<CurrencyRateToDisplay>()


    fun onCurrencySelected(pos: Int) {

        mCurrentCurrency.postValue(currencyRateList?.value?.get(pos))

    }


    /**
     * Returns Supported countries list
     */
    fun getAllSupportedCurrencies(): MutableLiveData<List<SupportedCurrency>?>? {

        countriesList = currencyAppRepository?.getSupportedCurrencies()

        return countriesList


    }

    /**
     * Returns currency rate list
     */

    fun getAllCurrencyRates(): MutableLiveData<List<CurrencyRateToDisplay>?>? {

        currencyRateList = currencyAppRepository?.getCurrencyRate()

        return currencyRateList

    }

    fun clearViewModel() {
        currencyAppRepository?.clear()
    }
}