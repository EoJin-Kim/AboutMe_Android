package com.ej.aboutme.dto

data class ResponseDto<T> (
    val status : String,
    val response : T
)