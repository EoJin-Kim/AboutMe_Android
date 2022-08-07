package com.ej.aboutme.api

import com.ej.aboutme.dto.request.*
import com.ej.aboutme.dto.response.*
import retrofit2.Call
import retrofit2.http.*

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

    @GET("/api/member/{memberId}")
    fun getMemberInfo(@Path("memberId") memberId:Long): Call<ResponseDto<MemberTotalInfoDto>>

    @GET("/api/member/team/{memberId}")
    fun getGroupListInfo(@Path("memberId") memberId:Long): Call<ResponseDto<MutableList<GroupSummaryDto>>>

    @PATCH("/api/member/memberinfo/{memberInfoId}")
    fun updateMemberInfo(@Path("memberInfoId") memberInfoId : Long,@Body memberInfoContentDto: MemberInfoContentDto) : Call<ResponseDto<List<MemberInfoDto>>>

    @PATCH("/api/member/{memberId}")
    fun updateMember(@Path("memberId") memberId : Long,@Body memberUpdateDto: MemberUpdateDto) : Call<ResponseDto<String>>

    @POST("/api/team")
    fun createGroup(@Body createTeamDto : CreateTeamDto) : Call<ResponseDto<MutableList<GroupSummaryDto>>>

    @GET("/api/team/{groupId}")
    fun getTotalGroupInfo(@Path("groupId") groupId : Long) : Call<ResponseDto<GroupTotalDto>>
}