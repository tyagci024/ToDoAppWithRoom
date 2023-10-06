package com.example.todoappwithroom.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todoappwithroom.model.Task


@Database(entities = [Task::class],version = 3,exportSchema = false)
abstract class TaskDatabase:RoomDatabase() {
    abstract fun taskDao():TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            var tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this)
            {
                val instance =
                    Room.databaseBuilder(context, TaskDatabase::class.java, "tasks").build()
                tempInstance = instance
                return instance
            }
        }

    }
}