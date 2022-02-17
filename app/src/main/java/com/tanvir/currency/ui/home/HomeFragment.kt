package com.tanvir.currency.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tanvir.currency.R
import com.tanvir.currency.adapter.SupportedCurrencyAdapter
import com.tanvir.currency.databinding.FragmentHomeBinding
import com.tanvir.currency.di.CurrencyApp
import com.tanvir.currency.model.CurrencyRateToDisplay
import com.tanvir.currency.model.SupportedCurrency
import com.tanvir.currency.viewmodel.CurrencyViewModel
import com.tanvir.currency.viewmodel.CurrencyViewModelFactory
import javax.inject.Inject


class HomeFragment : Fragment() , LifecycleObserver {

    @JvmField
    @Inject
    var factory : CurrencyViewModelFactory? = null
    private var currencyViewModel: CurrencyViewModel? =null
    private lateinit var binding: FragmentHomeBinding
    private lateinit var countriesAdapter: SupportedCurrencyAdapter
    private  var supportedCurrencies : List<SupportedCurrency?> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home, container, false
        )
        val mRootView = binding.root
        binding.lifecycleOwner = this
        return mRootView
    }

    @SuppressLint("NotifyDataSetChanged")


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        CurrencyApp.app?.appServiceComponent?.inject(this)

       currencyViewModel =  ViewModelProvider(this, factory!!)[CurrencyViewModel::class.java]
        countriesAdapter = SupportedCurrencyAdapter()

        binding.viewModel = currencyViewModel


        currencyViewModel!!.showLoading.set(true)
        currencyViewModel!!.getAllSupportedCurrencies()?.observe(viewLifecycleOwner) {
            currencyViewModel!!.showLoading.set(false)
            if (!it.isNullOrEmpty()) {

                supportedCurrencies = it

                currencyViewModel!!.showLoading.set(true)
                currencyViewModel!!.getAllCurrencyRates()?.observe(viewLifecycleOwner) { rates ->

                    currencyViewModel!!.showLoading.set(false)
                    if (!rates.isNullOrEmpty()) {


                        countriesAdapter.setSupportedCurrencies(supportedCurrencies as List<SupportedCurrency>)
                        countriesAdapter.setCurrencyRateList(rates)
                        setSpinner(rates)
                        setView()
                        countriesAdapter.notifyDataSetChanged()


                    } else {

                        Handler().postDelayed({
                            //doSomethingHere()

                            currencyViewModel!!.clearViewModel()
                            requireActivity().finish()
                            requireActivity().startActivity(requireActivity().intent)


                        }, 2000)

//                        currencyViewModel!!.getAllCurrencyRates()?.observe(viewLifecycleOwner,
//                            { spRate ->
//                                if (spRate != null) {
//                                    setSpinner(spRate)
//                                }
//
//                            })


                        // show error
                        //  Toast.makeText(context,"You have exceeded the maximum rate limitation allowed on your subscription plan. Please refer to the \\\"Rate Limits\\\" section of the API Documentation for details. ", Toast.LENGTH_LONG).show()

                    }
                }
            }

//            else {
//
//
//
//                //show error
//
//               // Toast.makeText(context,"You have exceeded the maximum rate limitation allowed on your subscription plan. Please refer to the \\\"Rate Limits\\\" section of the API Documentation for details. ", Toast.LENGTH_LONG).show()
//
//            }


        }


        currencyViewModel!!.mCurrentCurrency.observe(viewLifecycleOwner) {
            countriesAdapter.notifyDataSetChanged()
        }


    }

    private fun setView() {

        countriesAdapter.mViewModel = currencyViewModel
        binding.rvCountries.layoutManager = GridLayoutManager(
            activity,
            3
        )
        binding.rvCountries.adapter = countriesAdapter
        binding.rvCountries.isNestedScrollingEnabled = false
    }

    private fun setSpinner(list: List<CurrencyRateToDisplay>) {
        val adapter: ArrayAdapter<CurrencyRateToDisplay> = ArrayAdapter<CurrencyRateToDisplay>(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            list
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCurrencies.adapter = adapter

        binding.etMultiplier.placeCursorToEnd()
    }


    fun EditText.placeCursorToEnd() {
        this.setSelection(this.text.length)
    }

    override fun onDestroy() {
        super.onDestroy()
        currencyViewModel!!.clearViewModel()
    }
}