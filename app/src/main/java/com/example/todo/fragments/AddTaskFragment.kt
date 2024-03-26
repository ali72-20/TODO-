package com.example.todo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.todo.R
import com.example.todo.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment(){
    lateinit var viewBinding: FragmentAddTaskBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentAddTaskBinding.inflate(layoutInflater,container,false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        validateInput()
    }

    private fun validateInput() : Boolean {
       val text: String =  viewBinding.addTaskFaildPar.editText?.text.toString()
        if(text.isEmpty()) {
            viewBinding.addTaskFaildPar.error
            return false
        }
        return true
    }
}