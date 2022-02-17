package com.tanvir.currency

import com.tanvir.currency.model.CurrencyRateResponse
import com.tanvir.currency.model.SupportedCurrenciesResponse
import com.tanvir.currency.service.CurrencyDataService
import com.tanvir.currency.service.RetrofitInstance
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest{
    val mCount = 168
    val mRateCount = 168

    

    private val service: CurrencyDataService = RetrofitInstance.service


    @Test
    fun getCurrencyCount() {
        // mock data
         val compositeDisposable = CompositeDisposable()

        val requestToPayResponseObservable: Observable<SupportedCurrenciesResponse> =
            service.getSupportedCountries(BuildConfig.ACCESS_TOKEN)
        compositeDisposable.add(
            requestToPayResponseObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<SupportedCurrenciesResponse?>() {
                    override fun onNext(t: SupportedCurrenciesResponse?) {

                        if (t?.success == true) {
                           assert(t.supportedCurrenciesMap?.size  == mCount)

                        }


                    }

                    override fun onError(e: Throwable) {

                    }

                    override fun onComplete() {


                    }


                }))



}


    @Test
    fun getCurrencyRateCount() {

        val compositeDisposable = CompositeDisposable()
        val currencyRateObservable: Observable<CurrencyRateResponse> =
            service.getCurrencyRate(BuildConfig.ACCESS_TOKEN)
        compositeDisposable.add(
            currencyRateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<CurrencyRateResponse?>() {
                    override fun onNext(t: CurrencyRateResponse?) {
                        if(t?.success == true) {


                            assert(t.rates?.size  == mRateCount)


                            }






                    }

                    override fun onError(e: Throwable) {
//
                    }

                    override fun onComplete() {


                    }


                })
        )


    }

}