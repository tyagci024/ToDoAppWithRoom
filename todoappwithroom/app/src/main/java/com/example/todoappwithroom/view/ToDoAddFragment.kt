package com.example.todoappwithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoappwithroom.R
import com.example.todoappwithroom.adapter.RwAdapter
import com.example.todoappwithroom.databinding.FragmentToDoAddBinding
import com.example.todoappwithroom.databinding.FragmentToDoListBinding
import com.example.todoappwithroom.model.Task
import com.example.todoappwithroom.viewmodel.TaskViewModel


class ToDoAddFragment : Fragment() {
    private var _binding: FragmentToDoAddBinding?=null
    private val binding get() = _binding!!

    private val taskViewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToDoAddBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addButton.setOnClickListener {

            val priority = binding.prioritySpinner.selectedItem.toString()


            val task = Task(0,binding.taskName.text.toString(),binding.taskDetail.text.toString(),priority)
            taskViewModel.impTask(task)
            findNavController().navigate(R.id.action_toDoAddFragment_to_toDoListFragment)
        }

        return view
    }


}