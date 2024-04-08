package com.example.todo.ui.ui.showtask

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.TaskConst
import com.example.todo.database.myDataBase
import com.example.todo.databinding.FragmentTasksBinding
import com.example.todo.databinding.ItemTaskBinding
import com.example.todo.ui.ui.getDateOnly
import com.prolificinteractive.materialcalendarview.CalendarDay
import java.util.Calendar

class TasksFragment : Fragment() {
    lateinit var viewBinding: FragmentTasksBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentTasksBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
       adapter.onTaskClickListener = TasksAdapter.OnTaskClickListener { title, content, date, time,pos->
           val intent = Intent(requireContext(), EditeTask::class.java)
           intent.putExtra(TaskConst.title , title)
           intent.putExtra(TaskConst.content , content)
           intent.putExtra(TaskConst.date , date)
           intent.putExtra(TaskConst.time , time)
           intent.putExtra(TaskConst.pos,pos)
           startActivity(intent)
       }

    }




    override fun onResume() {
        super.onResume()
        retreiveTasksList()
    }

    val currentDate = Calendar.getInstance()
    fun retreiveTasksList() {
        val allTasks = myDataBase.getInstance().getDoa().getTaskByDate(currentDate.getDateOnly())
        adapter.changeData(allTasks)
    }

    val adapter = TasksAdapter()
    private fun setUpView() {
        viewBinding.recView.adapter = adapter

        viewBinding.calendarView.setOnDateChangedListener { widegt,
                                                            date, selected ->
            if (selected) {
                currentDate.set(date.year, date.month - 1, date.day)
                retreiveTasksList()
            }
        }
        viewBinding.calendarView.selectedDate = CalendarDay.today()
    }

}