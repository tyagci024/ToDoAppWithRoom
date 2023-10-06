package com.example.todoappwithroom.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todoappwithroom.R
import com.example.todoappwithroom.adapter.RwAdapter
import com.example.todoappwithroom.databinding.FragmentToDoListBinding
import com.example.todoappwithroom.viewmodel.TaskViewModel


class ToDoListFragment : Fragment() {
    private var _binding:FragmentToDoListBinding?=null
    private val binding get() = _binding!!
    private lateinit var rwAdapter: RwAdapter
    private val taskViewModel:TaskViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentToDoListBinding.inflate(inflater, container, false)
        binding.recyclerView.layoutManager= LinearLayoutManager(context)
        rwAdapter= RwAdapter()
        binding.recyclerView.adapter=rwAdapter
        taskViewModel.readAllData.observe(viewLifecycleOwner,{
            it?.let {
                rwAdapter.updateData(it)
                if(it.size==0){
                    Toast.makeText(context,"liste boş", Toast.LENGTH_LONG).show()
                }

            }
        })
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val taskId = rwAdapter.taskList[position].id

                taskViewModel.deleteTaskById(taskId)            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

        binding.addButton.setOnClickListener {
            findNavController().navigate(R.id.action_toDoListFragment_to_toDoAddFragment)
        }
    }
}