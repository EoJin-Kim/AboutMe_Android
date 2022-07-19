package com.ej.aboutme.data.repository

import com.ej.aboutme.data.entity.Member

interface MemberRepository {

    suspend fun createMember(member : Member)
    suspend fun updateMember(member: Member)
}