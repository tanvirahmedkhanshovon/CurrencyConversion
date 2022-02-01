package com.tanvir.currency.model

import com.squareup.moshi.Json

data class ErrorResponse( @field:Json(name = "code")
                          var code:String?,
                          @field:Json(name = "info")
                          var info:String?)
