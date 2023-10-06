package com.example.todoappwithroom.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize

@Entity(tableName = "task_table")

data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
     var taskName:String,
     var taskDesc:String,
    val priority: String
) : Parcelable
