package com.ej.aboutme.dto.request

import com.squareup.moshi.Json


data class JoinGroupDto(
    @Json(name = "member_id")
    val memberId : Long,
    @Json(name = "team_name")
    val groupName : String
)
