package com.example.todo.ui.ui.fragments
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.todo.database.model.Task
import com.example.todo.database.myDataBase
import com.example.todo.databinding.FragmentAddTaskBinding
import com.example.todo.ui.ui.formatDate
import com.example.todo.ui.ui.formatTime
import com.example.todo.ui.ui.getDateOnly
import com.example.todo.ui.ui.getTimeOnly
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.Calendar
class AddTaskFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTaskBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViews()
    }

    private fun setUpViews() {
        binding.selectDateTil.setOnClickListener{
            showDatePicker()
        }
        binding.selectTimeTil.setOnClickListener {
            showTimePicker()
        }
        binding.fabTaskDone.setOnClickListener {
            addTask()
        }
    }


    val calendar = Calendar.getInstance()
    private fun showTimePicker() {
       val timePicker = TimePickerDialog(
           requireContext(),
           {dialog,hourse,minutes->
               calendar.set(Calendar.HOUR_OF_DAY , hourse)
               calendar.set(Calendar.MINUTE,minutes)
               binding.selectTimeTv.text = calendar.formatTime()
               binding.selectTimeTil.error = null
           },
           calendar.get(Calendar.HOUR),
           calendar.get(Calendar.MINUTE),
           false
       )
        timePicker.show()
    }


    private fun showDatePicker() {
        val datePicker = DatePickerDialog(requireContext())
        datePicker.setOnDateSetListener { view, year, month, dayOfMonth ->
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,month)
            binding.selectDateTv.text = calendar.formatDate()
            binding.selectDateTil.error = null
        }
        datePicker.show()
    }

    private fun addTask() {
        if(!isValideTaskInput()){
            return
        }
        myDataBase.getInstance().getDoa()
            .insertTask(Task(
                title = binding.titleTil.text.toString(),
                content = binding.taskDiscriptionTil.text.toString(),
                date = calendar.getDateOnly(),
                time =  calendar.getTimeOnly()
            )
            )
        Toast.makeText(
            requireContext(),
            "Task saved successfully",
            Toast.LENGTH_LONG,
        ).show()
        dismiss( )
    }


    private fun isValideTaskInput(): Boolean {
        var isValid = true
        val title = binding.titleTil.text.toString()
        val description = binding.taskDiscriptionTil.text.toString()
        val date = binding.selectDateTv.text.toString()
        val time = binding.selectTimeTv.text.toString()
        if(title.isBlank()){
            binding.addTaskFaildPar.error = "You need to enter task title"
            isValid = false
        }else{
            binding.addTaskFaildPar.error = null
        }
        if(description.isBlank()){
            binding.descriptionTil.error = "You need to write task description"
            isValid = false
        }else{
            binding.descriptionTil.error = null
        }
        if(date.isBlank()){
            binding.selectDateTil.error = "Select Date!"
            isValid = false
        }
        if(time.isBlank()){
            binding.selectTimeTil.error = "Select Time!"
            isValid = false
        }
        return isValid
    }
}





