package com.aula.mobile_fej_todo.ui.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aula.mobile_fej_todo.Data.Data.Entity.Task
import com.aula.mobile_fej_todo.R

class TaskAdapter(private val onCheck: (Task) -> Unit) :
    ListAdapter<Task, TaskAdapter.TaskViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(task: Task) {
            val checkbox = itemView.findViewById<CheckBox>(R.id.check_task)
            val title = itemView.findViewById<TextView>(R.id.text_task_title)
            checkbox.isChecked = task.completed
            title.text = task.title

            checkbox.setOnClickListener {
                onCheck(task)
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(old: Task, newItem: Task) = old.id == newItem.id
        override fun areContentsTheSame(old: Task, newItem: Task) = old == newItem
    }
}
