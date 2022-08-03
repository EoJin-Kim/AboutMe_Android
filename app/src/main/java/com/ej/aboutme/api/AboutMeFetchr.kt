package com.ej.aboutme.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ej.aboutme.dto.response.ResponseDto
import com.ej.aboutme.dto.request.LoginDto
import com.ej.aboutme.dto.request.SignupDto
import com.ej.aboutme.dto.response.LoginResultDto
import com.ej.aboutme.dto.response.MemberTotalInfoDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//private const val SERVER_URL = "https://12524385-a283-4cf8-908f-5a07fab92462.mock.pstmn.io"
//private const val SERVER_URL = "https://85fa731a-7631-4d3e-abf4-aedc7dfa41d5.mock.pstmn.io"
private const val SERVER_URL = "http://58.225.113.85:8080"
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
    fun test():String{

        var result : String = ""
        val testResult = aboutMeApi.test()
        testResult.enqueue(object : Callback<ResponseDto<String>>{
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

    fun signup(signupDto: SignupDto): LiveData<String>{
        var result : MutableLiveData<String> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.signup(signupDto)
        aboutMeRequest.enqueue(object : Callback<ResponseDto<String>>{
            override fun onResponse(
                call: Call<ResponseDto<String>>,
                response: Response<ResponseDto<String>>
            ) {
                val aboutMeResponse : ResponseDto<String>? = response.body()
                result.value = aboutMeResponse!!.status
            }

            override fun onFailure(call: Call<ResponseDto<String>>, t: Throwable) {
                Log.d("http","request error")
            }
        })
        return result
    }
    fun login(loginDto: LoginDto): LiveData<ResponseDto<LoginResultDto>>{
        var result : MutableLiveData<ResponseDto<LoginResultDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.login(loginDto)
        aboutMeRequest.enqueue(object : Callback<ResponseDto<LoginResultDto>>{
            override fun onResponse(
                call: Call<ResponseDto<LoginResultDto>>,
                response: Response<ResponseDto<LoginResultDto>>
            ) {
                val aboutMeResponse : ResponseDto<LoginResultDto>? = response.body()
                result.value = aboutMeResponse!!
            }

            override fun onFailure(call: Call<ResponseDto<LoginResultDto>>, t: Throwable) {
                Log.d("http","request error")
            }
        })
        return  result
    }
    fun getMemberInfo(memberId : Long) :MutableLiveData<ResponseDto<MemberTotalInfoDto>>{

        var result : MutableLiveData<ResponseDto<MemberTotalInfoDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.getMemberInfo(memberId)
        aboutMeRequest.enqueue(object : Callback<ResponseDto<MemberTotalInfoDto>>{
            override fun onResponse(
                call: Call<ResponseDto<MemberTotalInfoDto>>,
                response: Response<ResponseDto<MemberTotalInfoDto>>
            ) {
                val aboutMeResponse : ResponseDto<MemberTotalInfoDto>? = response.body()
                result.value = aboutMeResponse!!
            }

            override fun onFailure(call: Call<ResponseDto<MemberTotalInfoDto>>, t: Throwable) {
                Log.d("http","request error")
            }
        })
        return result
    }
}