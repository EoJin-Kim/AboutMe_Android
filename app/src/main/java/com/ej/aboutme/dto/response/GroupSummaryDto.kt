package com.ej.aboutme.dto.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

//@JsonClass(generateAdapter = true)
data class GroupSummaryDto (
        @Json(name = "group_id")
        var groupId : Long,
        @Json(name = "team_name")
        var teamName : String,
        val summary : String,
        val count:Int
)