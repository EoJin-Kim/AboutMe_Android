package com.ej.aboutme.dto

import com.google.gson.annotations.SerializedName

data class ResponseDto<T> (
    val status : String,
    @SerializedName("response")
    val response : T
)