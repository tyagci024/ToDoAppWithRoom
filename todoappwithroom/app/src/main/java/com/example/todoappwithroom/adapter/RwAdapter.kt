package com.example.todoappwithroom.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithroom.R
import com.example.todoappwithroom.databinding.TaskItemBinding
import com.example.todoappwithroom.model.Task
import com.example.todoappwithroom.view.ToDoListFragmentDirections

class RwAdapter : RecyclerView.Adapter<RwAdapter.MyViewHolder>() {
    var taskList = emptyList<Task>()

    class MyViewHolder(private val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.task = task

            when (task.priority) {
                "Low" -> binding.layoutP.setBackgroundResource(R.color.lowPriority)
                "Medium" -> binding.layoutP.setBackgroundResource(R.color.mediumPriority)
                "High" -> binding.layoutP.setBackgroundResource(R.color.highPriority)
            }

            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TaskItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentTask = taskList[position]
        holder.bind(currentTask)

        holder.itemView.setOnClickListener {
            val action = ToDoListFragmentDirections.actionToDoListFragmentToToDoUpdateFragment(currentTask)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    fun updateData(list: List<Task>) {
        taskList = list
        notifyDataSetChanged()
    }
}
