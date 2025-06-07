package com.aula.mobile_fej_todo.Data.Data.Entity


    data class Task(
        val id: Long = 0,
        val title: String,
        val completed: Boolean = false
    )

