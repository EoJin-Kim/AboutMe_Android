package com.ej.aboutme.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Member {
    @PrimaryKey(autoGenerate = true)
    var uid: Int = 0
}