package com.ej.aboutme.dto.response

data class MemberInfoDto(
    val name:String,
    val job:String,
    val content:String,
    val phone:String,
    val email:String,
    val tag: MutableList<String>
)
