package com.betaccountant.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Story(
    @PrimaryKey(autoGenerate = true)
    val storyId: Int,
    @ForeignKey(entity = Task::class, parentColumns = ["levelNumber"], childColumns = ["levelNumber"])
    val taskId: Int,
    val storyTxt: String,
    val rightAnswer: String?,
)
