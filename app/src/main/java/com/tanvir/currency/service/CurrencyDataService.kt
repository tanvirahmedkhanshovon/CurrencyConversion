package com.tanvir.currency.service

import com.tanvir.currency.model.CurrencyRateResponse
import com.tanvir.currency.model.SupportedCurrenciesResponse
import io.reactivex.rxjava3.core.Observable

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyDataService

{

    @GET("/list")
     fun getSupportedCountries(@Query("access_key") accessKey:String): Observable<SupportedCurrenciesResponse>


    @GET("/live")
     fun getCurrencyRate(@Query("access_key") accessKey:String): Observable<CurrencyRateResponse>


}