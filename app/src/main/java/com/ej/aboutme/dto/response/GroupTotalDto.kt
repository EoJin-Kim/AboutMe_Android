package com.ej.aboutme.dto.response

data class GroupTotalDto(
    val groupName:String,
    val groupSummary : String,
    val count : Int,
    val memberSummary: MutableList<MemberSummaryDto>
)
