package com.ej.aboutme.dto.response

data class MemberTotalInfoDto(
    val image:String,
    val name:String,
    val job:String,
    val content:String,
    val phone:String,
    val email:String,
    val tag: MutableList<String>,
    val memberInfo : MutableList<MemberInfoDto>,
)
