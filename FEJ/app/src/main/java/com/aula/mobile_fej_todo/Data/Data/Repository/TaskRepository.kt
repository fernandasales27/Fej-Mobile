package com.aula.mobile_fej_todo.Data.Data.Repository

import com.aula.mobile_fej_todo.Data.Data.API.TaskService
import com.example.todo.data.api.TaskService
import com.example.todo.data.model.Task

class TaskRepository(private val api: TaskService.TaskService) {
        suspend fun getTasks() = api.getTasks()
        suspend fun addTask(title: String) = api.addTask(Task(title = title))
        suspend fun completeTask(id: Long) = api.completeTask(id)
        suspend fun deleteTask(id: Long) = api.deleteTask(id)
    }

