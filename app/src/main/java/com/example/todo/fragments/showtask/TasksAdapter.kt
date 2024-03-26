package com.example.todo.fragments.showtask

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.model.Task
import com.example.todo.databinding.FragmentTasksBinding

class TasksAdapter(val TaskList:List<Task>) : RecyclerView.Adapter<TasksAdapter.myViewHolder>() {

    class myViewHolder(private val itemBinding : FragmentTasksBinding) : RecyclerView.ViewHolder(itemBinding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        val viewBinding = FragmentTasksBinding.inflate(LayoutInflater.from(parent.context),
            parent
            ,false)
        return myViewHolder(viewBinding)
    }

    override fun getItemCount(): Int = TaskList.size;

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}