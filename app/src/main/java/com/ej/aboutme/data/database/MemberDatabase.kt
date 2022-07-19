package com.ej.aboutme.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ej.aboutme.data.entity.Member

@Database(entities = [Member::class], version = 1)
abstract class MemberDatabase : RoomDatabase() {
    abstract fun memberDao() : MemberDao
}