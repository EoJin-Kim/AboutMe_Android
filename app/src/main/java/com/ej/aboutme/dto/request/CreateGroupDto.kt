package com.ej.aboutme.dto.request

import com.google.gson.annotations.SerializedName

data class CreateGroupDto (
    @SerializedName("member_id")
    val memberId : Long,
    @SerializedName("team_name")
    val teamName : String,
    val summary : String
)