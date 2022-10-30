package com.ej.aboutme.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseDto<T> (
    val status : ResponseStatus,
    @SerializedName("response")
    val response : T
)