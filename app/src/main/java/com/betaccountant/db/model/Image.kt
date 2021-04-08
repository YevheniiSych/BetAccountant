package com.betaccountant.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class Image(
    @PrimaryKey(autoGenerate = true)
    val imageId: Int,
    @ForeignKey(entity = Task::class, parentColumns = ["levelNumber"], childColumns = ["levelNumber"])
    val taskId: Int,
    val url: String,
)
