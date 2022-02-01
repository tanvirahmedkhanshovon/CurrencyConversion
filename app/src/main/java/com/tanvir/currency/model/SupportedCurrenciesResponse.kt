package com.tanvir.currency.model


import com.squareup.moshi.Json


data class SupportedCurrenciesResponse(
    @field:Json(name = "success")
    var success:Boolean?,

    @field:Json(name = "error")
    var errorResponse :ErrorResponse?,
    @field:Json(name = "currencies")
    var supportedCurrenciesMap:Map<String,String>?


)