package com.ej.aboutme.api

import com.ej.aboutme.dto.ResponseDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface AboutMeApi {
    @POST("/api/member/signup")
    fun signup(): Call<ResponseDto<String>>

}