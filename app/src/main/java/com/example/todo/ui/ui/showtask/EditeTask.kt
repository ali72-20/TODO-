package com.example.todo.ui.ui.showtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.todo.TaskConst
import com.example.todo.database.model.Task
import com.example.todo.database.myDataBase
import com.example.todo.databinding.ActivityEditeTaskBinding
import com.example.todo.ui.ui.formatDate
import com.example.todo.ui.ui.formatTime
import com.example.todo.ui.ui.getDateOnly
import com.example.todo.ui.ui.getTimeOnly
import java.util.Calendar

class EditeTask : AppCompatActivity() {
    private lateinit var viewBinding: ActivityEditeTaskBinding
    lateinit var task: Task
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditeTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.appToolBar)
        initViews()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun initViews() {
        viewBinding.toolparTv.text = "To Do List"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        task = intent.getParcelableExtra(TaskConst.task,Task::class.java)?:Task()
        bindView(task)
        setUpViews()
    }

    private fun setUpViews() {
        viewBinding.selectTimeTil.setOnClickListener {
            showTimePicker()
        }
        viewBinding.selectDateTil.setOnClickListener {
            showDatePicker()
        }
        viewBinding.btnSave.setOnClickListener{
            onEditTasks()
        }
    }

    private fun showDatePicker() {
        val datePicker = DatePickerDialog(this)
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            viewBinding.selectDateTv.text = calendar.formatDate()
            viewBinding.selectTimeTv.error = null
        }
        datePicker.show()
    }

    private fun showTimePicker() {
        val timePickerDialog = TimePickerDialog(
            this,
            {dialog,hours,minutes->
              calendar.set(Calendar.HOUR,hours)
              calendar.set(Calendar.MINUTE,minutes)
                viewBinding.selectTimeTv.text = calendar.formatTime()
                viewBinding.selectTimeTil.error = null
            },
            calendar.get(Calendar.HOUR),
            calendar.get(Calendar.MINUTE),
            false
        )
        timePickerDialog.show()
    }


    val calendar = Calendar.getInstance()
    private fun onEditTasks() {
        if(!isValideTaskInput()){
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
            return
        }
        myDataBase.getInstance(this).getDoa().updateTask(task)
        Toast.makeText(this,"Task edited successfully", Toast.LENGTH_SHORT).show()
        finish()
    }
    private fun isValideTaskInput(): Boolean {
        var isValid = true
        val title = viewBinding.title.text.toString()
        val description = viewBinding.description.text.toString()
        val date = viewBinding.selectDateTv.text.toString()
        val time = viewBinding.selectTimeTv.text.toString()
        if(title.isBlank()){
            viewBinding.titleTil.error = "You need to enter task title"
            isValid = false
        }else{
            task.title = title
            viewBinding.titleTil.error = null
        }
        if(description.isBlank()){
            viewBinding.descriptionTil.error = "You need to write task description"
            isValid = false
        }else{
            task.content = description
            viewBinding.descriptionTil.error = null
        }
        if(date.isBlank()){
            viewBinding.selectDateTil.error = "Select Date!"
            isValid = false
        }
        if(time.isBlank()){
           viewBinding.selectTimeTil.error = "Select Time!"
            isValid = false
        }
        return isValid
    }

    private fun bindView(task:Task) {
        viewBinding.title.setText(task.title.toString())
        viewBinding.description.setText(task.content.toString())
        viewBinding.selectDateTv.setText(task.date.toString())
        viewBinding.selectTimeTv.setText(task.time.toString())
        viewBinding.selectDateTv.text = calendar.formatDate()
        viewBinding.selectTimeTv.text = calendar.formatTime()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}