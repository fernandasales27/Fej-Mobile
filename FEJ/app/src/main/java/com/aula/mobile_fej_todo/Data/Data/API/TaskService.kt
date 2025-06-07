package com.aula.mobile_fej_todo.Data.Data.API

import com.aula.mobile_fej_todo.Data.Data.Entity.Task
import retrofit2.http.*


    interface TaskService {
        @GET("tasks")
        suspend fun getTasks(): List<Task>

        @POST("tasks")
        suspend fun addTask(@Body task: Task): Task

        @PUT("tasks/{id}")
        suspend fun completeTask(@Path("id") id: Long): Task

        @DELETE("tasks/{id}")
        suspend fun deleteTask(@Path("id") id: Long)
    }

