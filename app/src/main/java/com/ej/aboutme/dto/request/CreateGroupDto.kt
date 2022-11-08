package com.ej.aboutme.dto.request

import com.squareup.moshi.Json

data class CreateGroupDto (
    @Json(name = "member_id")
    val memberId : Long,
    @Json(name = "team_name")
    val teamName : String,
    val summary : String
)