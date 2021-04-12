package com.betaccountant.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betaccountant.db.model.Fact
import com.betaccountant.db.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(tasks: List<Task>)

    @Query("SELECT * FROM Task WHERE levelNumber = :levelNumber AND isAddTask = 0")
    fun getTaskByLevel(levelNumber: Int): Task?

    @Query("SELECT * FROM Task WHERE levelNumber = :levelNumber AND isAddTask = 1")
    fun getAdditionalTaskByLevel(levelNumber: Int): Task?
}