package com.ej.aboutme.dto.response

import com.squareup.moshi.Json

data class ResponseDto<T> (
    val status : ResponseStatus,
    @Json(name = "response")
    val response : T
)