package com.ej.aboutme.api

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ej.aboutme.dto.request.*
import com.ej.aboutme.dto.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//private const val SERVER_URL = "https://12524385-a283-4cf8-908f-5a07fab92462.mock.pstmn.io"
//private const val SERVER_URL = "https://85fa731a-7631-4d3e-abf4-aedc7dfa41d5.mock.pstmn.io"
//private const val SERVER_URL = "http://39.118.206.192:8080"
private const val SERVER_URL = "http://10.10.20.137:8080"
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
    fun getMemberInfo(memberId : Long) :MutableLiveData<MemberTotalInfoDto>{

        var result : MutableLiveData<MemberTotalInfoDto> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.getMemberInfo(memberId)
        aboutMeRequest.enqueue(object : Callback<ResponseDto<MemberTotalInfoDto>>{
            override fun onResponse(
                call: Call<ResponseDto<MemberTotalInfoDto>>,
                response: Response<ResponseDto<MemberTotalInfoDto>>
            ) {
                val aboutMeResponse : ResponseDto<MemberTotalInfoDto>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(call: Call<ResponseDto<MemberTotalInfoDto>>, t: Throwable) {
                Log.d("http","request error")
            }
        })
        return result
    }

    fun getGroupList(memberId: Long) : MutableLiveData<MutableList<GroupSummaryDto>>{
        var result : MutableLiveData<MutableList<GroupSummaryDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.getGroupListInfo(memberId)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<MutableList<GroupSummaryDto>>>{
            override fun onResponse(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                response: Response<ResponseDto<MutableList<GroupSummaryDto>>>
            ) {
                val aboutMeResponse : ResponseDto<MutableList<GroupSummaryDto>>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                t: Throwable
            ) {
                Log.d("http","request error")
            }
        })
        return result
    }

    fun updateMemberInfo(memberInfoId:Long,memberInfoContentDto: MemberInfoContentDto) : MutableLiveData<List<MemberInfoDto>>{
        var result : MutableLiveData<List<MemberInfoDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.updateMemberInfo(memberInfoId,memberInfoContentDto)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<List<MemberInfoDto>>>{
            override fun onResponse(
                call: Call<ResponseDto<List<MemberInfoDto>>>,
                response: Response<ResponseDto<List<MemberInfoDto>>>
            ) {
                val aboutMeResponse : ResponseDto<List<MemberInfoDto>>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(
                call: Call<ResponseDto<List<MemberInfoDto>>>,
                t: Throwable
            ) {
                Log.d("http","request error")
            }
        })
        return result
    }

    fun updateMember(memberId : Long, memberUpdateDto: MemberUpdateDto) : LiveData<String>{
        var result : MutableLiveData<String> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.updateMember(memberId,memberUpdateDto)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<String>>{
            override fun onResponse(
                call: Call<ResponseDto<String>>,
                response: Response<ResponseDto<String>>
            ) {
                val aboutMeResponse : ResponseDto<String>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(
                call: Call<ResponseDto<String>>,
                t: Throwable
            ) {
                Log.d("http","request error")
            }
        })
        return result
    }

    fun createGroup(createGroupDto: CreateGroupDto):LiveData<MutableList<GroupSummaryDto>>{
        var result : MutableLiveData<MutableList<GroupSummaryDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.createGroup(createGroupDto)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<MutableList<GroupSummaryDto>>>{
            override fun onResponse(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                response: Response<ResponseDto<MutableList<GroupSummaryDto>>>
            ) {
                val aboutMeResponse : ResponseDto<MutableList<GroupSummaryDto>>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                t: Throwable
            ) {
                Log.d("http","request error")
            }
        })
        return result

    }

    fun getTotalGroupInfo(groupId : Long) : LiveData<GroupTotalDto>{
        var result : MutableLiveData<GroupTotalDto> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.getTotalGroupInfo(groupId)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<GroupTotalDto>> {
            override fun onResponse(
                call: Call<ResponseDto<GroupTotalDto>>,
                response: Response<ResponseDto<GroupTotalDto>>
            ) {
                val aboutMeResponse : ResponseDto<GroupTotalDto>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(call: Call<ResponseDto<GroupTotalDto>>, t: Throwable) {
                Log.d("http", "request error")
            }
        })
        return result
    }
    fun joinGroup(joinGroupDto: JoinGroupDto) : LiveData<MutableList<GroupSummaryDto>>{
        val result : MutableLiveData<MutableList<GroupSummaryDto>> = MutableLiveData()
        val aboutMeRequest = aboutMeApi.joinGroup(joinGroupDto)
        aboutMeRequest.enqueue(object :Callback<ResponseDto<MutableList<GroupSummaryDto>>>{
            override fun onResponse(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                response: Response<ResponseDto<MutableList<GroupSummaryDto>>>
            ) {
                val aboutMeResponse : ResponseDto<MutableList<GroupSummaryDto>>? = response.body()
                result.value = aboutMeResponse!!.response
            }

            override fun onFailure(
                call: Call<ResponseDto<MutableList<GroupSummaryDto>>>,
                t: Throwable
            ) {
                Log.d("http","request error")
            }
        })
        return result

    }

}



