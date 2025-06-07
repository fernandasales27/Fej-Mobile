package com.aula.mobile_fej_todo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aula.mobile_fej_todo.Data.Data.API.TaskService
import com.aula.mobile_fej_todo.Data.Data.Repository.TaskRepository
import com.aula.mobile_fej_todo.R
import com.aula.mobile_fej_todo.ViewModel.TaskViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AddTaskFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)
        val editText = view.findViewById<EditText>(R.id.edit_task_title)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(TaskService::class.java)
        val repository = TaskRepository(api)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) = TaskViewModel(repository) as T
        })[TaskViewModel::class.java]

        view.findViewById<Button>(R.id.btn_save_task).setOnClickListener {
            val title = editText.text.toString()
            if (title.isNotEmpty()) {
                viewModel.addTask(title)
                findNavController().navigateUp()
            }
        }

        return view
    }
}
