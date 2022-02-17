package com.tanvir.currency.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.tanvir.currency.R
import com.tanvir.currency.databinding.ItemCountriesBinding
import com.tanvir.currency.model.CurrencyRateToDisplay
import com.tanvir.currency.model.SupportedCurrency
import com.tanvir.currency.viewmodel.CurrencyViewModel


class SupportedCurrencyAdapter() :
    RecyclerView.Adapter<SupportedCurrencyAdapter.CountriesViewHolder>() {

    var suppoertedCurrencyList: List<SupportedCurrency> = ArrayList()
    private var currencyRateList: List<CurrencyRateToDisplay> = ArrayList()
    var mViewModel: CurrencyViewModel? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val viewBinding: ItemCountriesBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_countries, parent, false
        )
        return CountriesViewHolder(viewBinding)
    }


    override fun getItemCount(): Int {
        return currencyRateList.size
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun setSupportedCurrencies(countries: List<SupportedCurrency>) {
        this.suppoertedCurrencyList = countries
        // notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setCurrencyRateList(rates: List<CurrencyRateToDisplay>) {
        this.currencyRateList = rates
        notifyDataSetChanged()
    }

    inner class CountriesViewHolder(private val viewBinding: ItemCountriesBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun onBind(position: Int) {
            val row = suppoertedCurrencyList[position]
            viewBinding.countries = row
            viewBinding.currency = currencyRateList[position]
            viewBinding.viewmodel = mViewModel
        }
    }


}


