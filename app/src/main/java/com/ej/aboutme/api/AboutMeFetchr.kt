package com.ej.aboutme.api

import android.util.Log
import com.ej.aboutme.dto.ResponseDto
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.request.SignupDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//private const val SERVER_URL = "https://85fa731a-7631-4d3e-abf4-aedc7dfa41d5.mock.pstmn.io"
private const val SERVER_URL = "https://85fa731a-7631-4d3e-abf4-aedc7dfa41d5.mock.pstmn.io"
class AboutMeFetchr {
    private val aboutMeApi: AboutMeApi

    init {
//        val client = OkHttpClient.Builder()
//                .addInterceptor(PhotoInterceptor())
//                .build()
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
//            .client(client)
            .build()
        aboutMeApi = retrofit.create(AboutMeApi::class.java)

    }

    fun signup(signupDto: SignupDto): String{
        var result : String = ""
        val aboutMeRequest = aboutMeApi.signup(signupDto)
        aboutMeRequest.enqueue(object : Callback<ResponseDto<String>>{
            override fun onResponse(
                call: Call<ResponseDto<String>>,
                response: Response<ResponseDto<String>>
            ) {
                val aboutMeResponse : ResponseDto<String>? = response.body()
                result = aboutMeResponse!!.response
            }

            override fun onFailure(call: Call<ResponseDto<String>>, t: Throwable) {
                Log.d("http","request error")
                result = "error"
            }
        })
        return result
    }
    fun login(loginDto: LoginDto): Call<ResponseDto<String>>{
        return aboutMeApi.login(loginDto)
    }
}