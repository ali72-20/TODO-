package com.example.todo.ui.ui.showtask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.database.myDataBase
import com.example.todo.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {
    lateinit var viewBinding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        retreiveTasksList()
    }


    private fun retreiveTasksList() {
        val allTasks = myDataBase.getInstance().getDoa().getAllTasks()
        adapter.changeData(allTasks)
    }



    val adapter = TasksAdapter()
    private fun setUpRecyclerView() {
        viewBinding.recView.adapter = adapter
    }
}