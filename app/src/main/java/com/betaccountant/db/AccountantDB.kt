package com.betaccountant.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.betaccountant.db.dao.ImageDao
import com.betaccountant.db.dao.StoryDao
import com.betaccountant.db.dao.TaskDao
import com.betaccountant.db.model.Image
import com.betaccountant.db.model.Story
import com.betaccountant.db.model.Task


@Database(
    entities = [Task::class, Image::class, Story::class],
    version = 1
)
abstract class AccountantDB : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun imageDao(): ImageDao
    abstract fun storyDao(): StoryDao

    companion object {
        private const val DATABASE = "Accountant"

        // For Singleton instantiation
        @Volatile
        private var instance: AccountantDB? = null

        fun getInstance(context: Context): AccountantDB {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AccountantDB {
            return Room.databaseBuilder(context, AccountantDB::class.java, DATABASE)
                .build()
        }
    }
}