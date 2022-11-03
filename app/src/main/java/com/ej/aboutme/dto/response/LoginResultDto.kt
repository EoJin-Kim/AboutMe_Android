package com.ej.aboutme.dto.response

import com.squareup.moshi.Json

data class LoginResultDto(
    @Json(name = "member_id")
    val memberId:Long,
    val email:String
    )
