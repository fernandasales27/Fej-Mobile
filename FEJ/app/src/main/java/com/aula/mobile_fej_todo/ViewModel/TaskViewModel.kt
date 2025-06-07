package com.aula.mobile_fej_todo.ViewModel

import com.aula.mobile_fej_todo.Data.Data.Repository.TaskRepository

import androidx.lifecycle.*
import com.aula.mobile_fej_todo.Data.Data.Entity.Task

import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    fun loadTasks() {
        viewModelScope.launch {
            _tasks.value = repository.getTasks()
        }
    }

    fun addTask(title: String) {
        viewModelScope.launch {
            repository.addTask(title)
            loadTasks()
        }
    }

    fun completeTask(id: Long) {
        viewModelScope.launch {
            repository.completeTask(id)
            loadTasks()
        }
    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            repository.deleteTask(id)
            loadTasks()
        }
    }
}
