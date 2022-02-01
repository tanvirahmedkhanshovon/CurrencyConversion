package com.tanvir.currency.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.tanvir.currency.model.CurrencyRateToDisplay


object CurrencyDataBindingAdapter {

    @JvmStatic
    @BindingAdapter("currencyCode", "currencyRate", "multiplier"
        ,"currentCurrency")
    fun setCurrencyRate(
        tv: AppCompatTextView,
        code: String?,
        rate: Double,
        multiplier: ObservableField<String>,
        currentCurrency: MutableLiveData<CurrencyRateToDisplay>) {

        if(multiplier?.get().isNullOrEmpty()){
            tv.text = " -- "
            return
        }
        val selectedCurrencyRate=currentCurrency.value?.rate
        if(selectedCurrencyRate!=null) {
            try {
                val multi = multiplier.get()
                val displayData =
                    (rate.toBigDecimal() / selectedCurrencyRate.toBigDecimal()) * multi?.toBigDecimal()!!
                           tv.text = "$displayData"
            }catch ( ex : Exception){
                tv.text = " -- "

            }
        }else{
            tv.text = " -- "
        }

    }

    @BindingAdapter("image")
    @JvmStatic
    fun setImage(image: AppCompatImageView, code: String?) {

        if (!code.isNullOrEmpty()){

            val url="https://www.xe.com/themes/xe/images/flags/big/${code.toString().lowercase()}.png"
            Glide.with(image.context).load(url).centerCrop()
                .into(image)
        }



    }
}