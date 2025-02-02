package com.kenruizinoue.roomtemplate03

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Int? = null,
    val name: String,
    val age: Int
)