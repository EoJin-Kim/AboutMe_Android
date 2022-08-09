package com.ej.aboutme.dto.request

import com.google.gson.annotations.SerializedName

data class JoinGroupDto(
    @SerializedName("member_id")
    val memberId : Long,
    @SerializedName("team_name")
    val groupName : String
)
