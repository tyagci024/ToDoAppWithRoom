package com.example.todoappwithroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todoappwithroom.model.Task
import com.example.todoappwithroom.service.TaskDatabase
import com.example.todoappwithroom.service.TaskRepo
import kotlinx.coroutines.launch

class TaskViewModel(application: Application):AndroidViewModel(application) {
    var readAllData: LiveData<List<Task>>
    var repostory : TaskRepo

    init {
        val taskDao=TaskDatabase.getDatabase(application).taskDao()
        repostory= TaskRepo(taskDao)
        readAllData=taskDao.getAllTasks()
    }

    fun impTask(task: Task){
        viewModelScope.launch {
            repostory.addTask(task)

        }

    }
    fun deleteAll(){
        viewModelScope.launch {
            repostory.deleteAll()
        }
    }

    fun removeTask(task: Task){
        viewModelScope.launch {
            repostory.deleteTask(task)
        }

    }

    fun update(task: Task){
        viewModelScope.launch {
            repostory.updateTask(task)

        }

    }

    fun deleteTaskById(id:Int){
        viewModelScope.launch {
            repostory.deleteTaskById(id)
        }
    }

    fun delete(task: Task){
        viewModelScope.launch {
            repostory.deleteTask(task)
        }

    }


}