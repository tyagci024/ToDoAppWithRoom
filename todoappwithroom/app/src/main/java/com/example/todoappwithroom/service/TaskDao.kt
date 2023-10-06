package com.example.todoappwithroom.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todoappwithroom.model.Task

@Dao

interface TaskDao {


    @Insert
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)


    @Query("SELECT * from task_table order by id ASC")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getReminderById(id: Int): Task


    @Query("DELETE FROM task_table WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("DELETE FROM task_table")
    suspend fun deleteAll()






}