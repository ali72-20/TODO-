package com.example.todo.ui.ui.showtask

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
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
    lateinit var viewBinding: ActivityEditeTaskBinding
    lateinit var title : String
    lateinit var content: String
     var date : Long? = null
     var time : Long? = null
     var position : Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityEditeTaskBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.appToolBar)
        initViews()
    }

    private fun initViews() {
        viewBinding.toolparTv.text = "To Do List"
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = intent.getStringExtra(TaskConst.title)?:""
        content = intent.getStringExtra(TaskConst.content)?:""
        date = intent.getLongExtra(TaskConst.date,0)
        time = intent.getLongExtra(TaskConst.time,0)
        position =  intent.getIntExtra(TaskConst.pos,0)
        pushView()
        setUpViews()
    }

    private fun setUpViews() {
        viewBinding.selectTimeTil.setOnClickListener {
            showTimePicker()
        }
        viewBinding.selectDateTil.setOnClickListener {
            showDatePicker()
        }
        viewBinding.btnSave.setOnClickListener{save->
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
    val adapter = TasksAdapter()
    private fun onEditTasks() {
        if(!isValideTaskInput()){
            return
        }
        myDataBase.getInstance().getDoa().updateTask(Task(
            title = viewBinding.title.toString(),
            content = viewBinding.description.toString(),
            date = calendar.getDateOnly(),
            time = calendar.getTimeOnly()
        ))
        adapter.updateTask(position!!)
        Toast.makeText(this,"Task edited successfully", Toast.LENGTH_SHORT).show()
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
            viewBinding.titleTil.error = null
        }
        if(description.isBlank()){
            viewBinding.descriptionTil.error = "You need to write task description"
            isValid = false
        }else{
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

    private fun pushView() {
        viewBinding.title.setText(title)
        viewBinding.description.setText(content)
        viewBinding.selectDateTv.setText(date.toString())
        viewBinding.selectTimeTv.setText(time.toString())
        viewBinding.selectDateTv.text = calendar.formatDate()
        viewBinding.selectTimeTv.text = calendar.formatTime()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }


}