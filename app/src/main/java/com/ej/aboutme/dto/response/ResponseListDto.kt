package com.ej.aboutme.dto.response

import com.google.gson.annotations.SerializedName

data class ResponseListDto<T>(
    val status : String,
    val response : MutableList<T>
)
