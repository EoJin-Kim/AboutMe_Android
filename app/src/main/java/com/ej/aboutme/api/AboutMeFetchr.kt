package com.ej.aboutme.api

import com.ej.aboutme.dto.ResponseDto
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.request.SignupDto
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BACK_URL = "https://api.flickr.com"
class AboutMeFetchr {
    private val aboutMeApi: AboutMeApi

    init {
//        val client = OkHttpClient.Builder()
//                .addInterceptor(PhotoInterceptor())
//                .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(BACK_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
        aboutMeApi = retrofit.create(AboutMeApi::class.java)

    }

    fun signup(signupDto: SignupDto): Call<ResponseDto<String>>{
        return aboutMeApi.signup(signupDto)
    }
    fun login(loginDto: LoginDto): Call<ResponseDto<String>>{
        return aboutMeApi.login(loginDto)
    }
}