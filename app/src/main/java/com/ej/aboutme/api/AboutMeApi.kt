package com.ej.aboutme.api

import com.ej.aboutme.dto.response.ResponseDto
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.request.SignupDto
import com.ej.aboutme.dto.response.LoginResultDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface AboutMeApi {

    @Headers("Content-Type: application/json")
    @GET("/test")
    fun test(): Call<ResponseDto<String>>

    @Headers("Content-Type: application/json")
    @POST("/api/member/signup")
    fun signup(@Body signupDto: SignupDto): Call<ResponseDto<String>>


    @Headers("Content-Type: application/json")
    @POST("/api/member/login")
    fun login(@Body loginDto: LoginDto): Call<ResponseDto<LoginResultDto>>

}