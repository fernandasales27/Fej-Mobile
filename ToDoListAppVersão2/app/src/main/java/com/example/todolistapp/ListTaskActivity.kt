package com.example.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todolistapp.api.ApiService
import com.example.todolistapp.databinding.ActivityListTaskBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerViewTasks.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)

        // ⚠️ Chamada para a API real
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val tasks = ApiService.taskApi.getTasks()
                runOnUiThread {
                    binding.recyclerViewTasks.adapter = TaskAdapter(tasks)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}
