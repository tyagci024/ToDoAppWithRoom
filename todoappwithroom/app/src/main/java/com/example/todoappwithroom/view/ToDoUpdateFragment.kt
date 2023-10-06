package com.example.todoappwithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoappwithroom.R
import com.example.todoappwithroom.adapter.RwAdapter
import com.example.todoappwithroom.databinding.FragmentToDoAddBinding
import com.example.todoappwithroom.databinding.FragmentToDoUpdateBinding
import com.example.todoappwithroom.model.Task
import com.example.todoappwithroom.viewmodel.TaskViewModel


class ToDoUpdateFragment : Fragment() {
    private var _binding: FragmentToDoUpdateBinding?=null
    private val binding get() = _binding!!
    private val taskViewModel: TaskViewModel by viewModels()
    private val args by navArgs<ToDoUpdateFragmentArgs>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToDoUpdateBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.taskNameUpdate.setText(args.currentTask.taskName)
        binding.taskDetailUpdate.setText(args.currentTask.taskDesc)
        val priorities = listOf("Low", "Medium", "High")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, priorities)

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Apply the adapter to the spinner
        binding.prioritySpinner.adapter = adapter

        // Set selection based on the current priority
        binding.prioritySpinner.setSelection(priorities.indexOf(args.currentTask.priority))





        binding.update.setOnClickListener {

            val task = Task(args.currentTask.id,binding.taskNameUpdate.text.toString(),binding.taskDetailUpdate.text.toString(),binding.prioritySpinner.selectedItem.toString())
            taskViewModel.update(task)
            findNavController().navigate(R.id.action_toDoUpdateFragment_to_toDoListFragment)

        }
        binding.delete.setOnClickListener {
            taskViewModel.deleteTaskById(args.currentTask.id)
            findNavController().navigate(R.id.action_toDoUpdateFragment_to_toDoListFragment)


        }


        return view
    }


}