package com.betaccountant.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.betaccountant.db.model.Fact

@Dao
interface FactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(facts: List<Fact>)

    @Query("SELECT * FROM Fact WHERE levelNumber = :levelNumber")
    fun getFactsByLevel(levelNumber: Int): List<Fact>?
}