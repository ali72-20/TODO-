package com.example.todo.ui.ui.showtask

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.database.model.Task
import com.example.todo.databinding.ItemTaskBinding
import java.util.Calendar

class TasksAdapter(var tasks: MutableList<Task>? = null) : RecyclerView.Adapter<TasksAdapter.ViewHolder>(){
    class ViewHolder(val binding:ItemTaskBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(task : Task){
            binding.title.text = task.title.toString()
            binding.description.text = task.content.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding : ItemTaskBinding =
            ItemTaskBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(itemBinding)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun changeData(newTasks: List<Task>){
        if(tasks == null)
            tasks = mutableListOf()
        tasks?.clear()
        tasks?.addAll(newTasks)
        notifyDataSetChanged()
    }
    fun updateTask(pos:Int){
        notifyItemChanged(pos)
    }
    override fun getItemCount(): Int = tasks?.size?:0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks!![position]
        holder.bind(task)
        onTaskClickListener?.let {Listener->
            holder.binding.title.setOnClickListener {
                val title : String = task.title.toString()
                val content : String = task.content.toString()
                val date : Long? = task.date
                val time : Long? = task.time
                Listener.onTackClick(title,content,date,time,position)
            }
        }


        onDeletedTaskListener?.let {listener ->
            holder.binding.deleteTil.setOnClickListener {
                listener.onDeletedTaskClick(task,position)
            }
        }


        onTaskDoneClickListener?.let { listener->
            holder.binding.btnTaskIsDone.setOnClickListener{
                listener.onTaskDone()
            }
        }
    }

    var onTaskClickListener:OnTaskClickListener?=null
    fun interface OnTaskClickListener{
        fun onTackClick(title:String,content:String, date:Long?,time:Long?,position: Int)
    }

    var onDeletedTaskListener : OnDeletedTaskListener? = null
    fun interface OnDeletedTaskListener{
        fun onDeletedTaskClick(task: Task,position: Int)
    }

    var onTaskDoneClickListener : OnTaskDoneClickListener?= null

    fun interface OnTaskDoneClickListener {
        fun onTaskDone()
    }
}