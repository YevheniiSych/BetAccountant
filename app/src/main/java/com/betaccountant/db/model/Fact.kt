package com.betaccountant.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Fact(
    @PrimaryKey(autoGenerate = true)
    val factId: Int? = null,
    val levelNumber: Int,
    val text: String,
    val isTrue: Boolean,
    val groupId: Int = 0
)