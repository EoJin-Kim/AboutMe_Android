package com.ej.aboutme.dto.response

import com.google.gson.annotations.SerializedName

data class LoginResultDto(
    @SerializedName("member_id")
    val memberId:String,
    val email:String
    )
