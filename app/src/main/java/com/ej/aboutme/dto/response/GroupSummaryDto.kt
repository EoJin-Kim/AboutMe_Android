package com.ej.aboutme.dto.response

import com.google.gson.annotations.SerializedName

data class GroupSummaryDto (
        @SerializedName("group_id")
        val groupId : Long,
        @SerializedName("team_name")
        val teamName : String,
        val summary : String,
        val count:Int
)