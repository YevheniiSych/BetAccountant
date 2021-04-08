package com.betaccountant.db.model

import androidx.room.Entity

@Entity
data class Fact(
    val text: String,
    val isTrue: Boolean,
    val groupId: Int = 0
)