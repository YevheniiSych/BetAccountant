package com.betaccountant.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val taskId: Int = 0,
    val levelNumber: Int,
    val question: String?,
    val rightAnswer: String? = null,
    val taskDesc: String? = null,
    val isAddTask: Boolean? = false
)
