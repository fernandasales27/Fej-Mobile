package com.aula.mobile_fej_todo.Data.Data.Repository

import com.aula.mobile_fej_todo.Data.Data.API.TaskService
import com.aula.mobile_fej_todo.Data.Data.Entity.Task


class TaskRepository(private val api: TaskService) {
        suspend fun getTasks() = api.getTasks()
        suspend fun addTask(title: String) = api.addTask(Task(title = title))
        suspend fun completeTask(id: Long) = api.completeTask(id)
        suspend fun deleteTask(id: Long) = api.deleteTask(id)
    }

