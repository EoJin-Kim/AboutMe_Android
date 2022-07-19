package com.ej.aboutme.data.repository

import android.app.Application
import androidx.room.Room
import com.ej.aboutme.data.database.MemberDatabase
import com.ej.aboutme.data.entity.Member

class MemberRepositoryImpl(application: Application) : MemberRepository{
    private val db = Room.databaseBuilder(
        application,
        MemberDatabase::class.java,
        "member-db"
    ).build()

    override suspend fun createMember(member: Member) {
        TODO("Not yet implemented")
    }

    override suspend fun updateMember(member: Member) {
        TODO("Not yet implemented")
    }
}