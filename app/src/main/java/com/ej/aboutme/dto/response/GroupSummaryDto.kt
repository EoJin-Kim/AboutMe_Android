package com.ej.aboutme.dto.response

import com.squareup.moshi.Json

data class GroupSummaryDto (
        @Json(name = "group_id")
        var group_id : Long,
        @Json(name = "team_name")
        var teamName : String,
        val summary : String,
        val count:Int
)