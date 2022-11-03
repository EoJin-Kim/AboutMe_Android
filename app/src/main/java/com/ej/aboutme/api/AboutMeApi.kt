package com.ej.aboutme.api

import com.ej.aboutme.dto.request.*
import com.ej.aboutme.dto.response.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface AboutMeApi {

    @Headers("Content-Type: application/json")
    @GET("/test")
    suspend fun test(): ResponseDto<String>

    @Headers("Content-Type: application/json")
    @POST("/api/member/signup")
    suspend fun signup(@Body signupDto: SignupDto): ResponseDto<String>

    @Headers("Content-Type: application/json")
    @POST("/api/member/login")
    suspend fun login(@Body loginDto: LoginDto): ResponseDto<LoginResultDto>

    @GET("/api/member/{memberId}")
    suspend fun getMemberInfo(@Path("memberId") memberId:Long): ResponseDto<MemberTotalInfoDto>

    @GET("/api/member/team/{memberId}")
    suspend fun getGroupListInfo(@Path("memberId") memberId:Long): ResponseDto<MutableList<GroupSummaryDto>>

    @PATCH("/api/member/memberinfo/{memberInfoId}")
    suspend fun updateMemberInfo(@Path("memberInfoId") memberInfoId : Long,@Body memberInfoContentDto: MemberInfoContentDto) : ResponseDto<List<MemberInfoDto>>

    @PATCH("/api/member/{memberId}")
    @Multipart
    suspend fun updateMember(
        @Path("memberId") memberId : Long,
        @Part memberImage : MultipartBody.Part? = null,
        @PartMap params : Map<String,@JvmSuppressWildcards RequestBody>,
        @Part tags : List<MultipartBody.Part>
    ) : ResponseDto<String>

    @POST("/api/team")
    suspend fun createGroup(@Body createGroupDto : CreateGroupDto) : ResponseDto<MutableList<GroupSummaryDto>>

    @GET("/api/team/{groupId}")
    suspend fun getTotalGroupInfo(@Path("groupId") groupId : Long) : ResponseDto<GroupTotalDto>

    @POST("/api/team/join")
    suspend fun joinGroup(@Body joinGroupDto: JoinGroupDto) : ResponseDto<MutableList<GroupSummaryDto>>

//    @Headers("Content-Type: application/json")
//    @GET("/test")
//    suspend fun test(): Call<ResponseDto<String>>
//
//    @Headers("Content-Type: application/json")
//    @POST("/api/member/signup")
//    suspend fun signup(@Body signupDto: SignupDto): Call<ResponseDto<String>>
//
//
//    @Headers("Content-Type: application/json")
//    @POST("/api/member/login")
//    suspend fun login(@Body loginDto: LoginDto): Call<ResponseDto<LoginResultDto>>
//
//    @GET("/api/member/{memberId}")
//    suspend fun getMemberInfo(@Path("memberId") memberId:Long): Call<ResponseDto<MemberTotalInfoDto>>
//
//    @GET("/api/member/team/{memberId}")
//    suspend fun getGroupListInfo(@Path("memberId") memberId:Long): Call<ResponseDto<MutableList<GroupSummaryDto>>>
//
//    @PATCH("/api/member/memberinfo/{memberInfoId}")
//    suspend fun updateMemberInfo(@Path("memberInfoId") memberInfoId : Long,@Body memberInfoContentDto: MemberInfoContentDto) : Call<ResponseDto<List<MemberInfoDto>>>
//
//    @PATCH("/api/member/{memberId}")
//    @Multipart
//    suspend fun updateMember(
//        @Path("memberId") memberId : Long,
//        @Part memberImage : MultipartBody.Part? = null,
//        @PartMap params : Map<String,@JvmSuppressWildcards RequestBody>,
//        @Part tags : List<MultipartBody.Part>
//    ) : Call<ResponseDto<String>>
//
//    @POST("/api/team")
//    suspend fun createGroup(@Body createGroupDto : CreateGroupDto) : Call<ResponseDto<MutableList<GroupSummaryDto>>>
//
//    @GET("/api/team/{groupId}")
//    suspend fun getTotalGroupInfo(@Path("groupId") groupId : Long) : Call<ResponseDto<GroupTotalDto>>
//
//    @POST("/api/team/join")
//    suspend fun joinGroup(@Body joinGroupDto: JoinGroupDto) : Call<ResponseDto<MutableList<GroupSummaryDto>>>
}