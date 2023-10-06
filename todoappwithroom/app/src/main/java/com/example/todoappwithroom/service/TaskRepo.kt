package com.example.todoappwithroom.service

import androidx.lifecycle.LiveData
import com.example.todoappwithroom.model.Task


class TaskRepo(private val taskDao: TaskDao) {

    val readAllData: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun addTask(task: Task){
        taskDao.addTask(task)
    }
    suspend fun deleteTask(drug: Task){
        taskDao.deleteTask(drug)

    }

    suspend fun deleteTaskById(id:Int){
        taskDao.deleteTaskById(id)
    }

    suspend fun updateTask(drug: Task){
        taskDao.updateTask(drug)

    }
    suspend fun deleteAll(){
        taskDao.deleteAll()
    }
    fun getReminderById(id: Int): Task {
        return taskDao.getReminderById(id)
    }

}