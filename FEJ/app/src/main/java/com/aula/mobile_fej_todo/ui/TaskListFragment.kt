package com.aula.mobile_fej_todo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aula.mobile_fej_todo.Data.Data.API.TaskService
import com.aula.mobile_fej_todo.Data.Data.Repository.TaskRepository
import com.aula.mobile_fej_todo.R
import com.aula.mobile_fej_todo.ViewModel.TaskViewModel
import com.aula.mobile_fej_todo.ui.Adapter.TaskAdapter

class TaskListFragment : Fragment() {
    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_task_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_tasks)
        adapter = TaskAdapter { task -> viewModel.completeTask(task.id) }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        view.findViewById<Button>(R.id.btn_add_task).setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_addTaskFragment)
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/") // Android Emulator
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api = retrofit.create(TaskService.TaskService::class.java)
        val repository = TaskRepository(api)
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) = TaskViewModel(repository) as T
        })[TaskViewModel::class.java]

        viewModel.tasks.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        viewModel.loadTasks()

        return view
    }
}
