package com.tanvir.currency.model


import com.squareup.moshi.Json


data class CurrencyRateResponse (

    @field:Json(name = "success")
    var success:Boolean?,

    @field:Json(name = "error")
    var errorResponse :ErrorResponse?,

    @field:Json(name = "source")
    var source:String?,

    @field:Json(name = "quotes")
    var rates:Map<String,Double>?


)