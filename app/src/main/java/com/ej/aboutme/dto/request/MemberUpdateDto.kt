package com.ej.aboutme.dto.request

data class MemberUpdateDto(
    val name : String,
    val job : String,
    val phone:String,
    val content: String,
    val tag : MutableList<String>
)
