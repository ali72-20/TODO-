package com.example.todo.ui.ui.home

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.todo.R
import com.example.todo.database.myDataBase
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.ui.ui.fragments.AddTaskFragment
import com.example.todo.ui.ui.fragments.SettingsFragment
import com.example.todo.ui.ui.showtask.TasksFragment

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
    }
    private fun initView() {
        viewBinding.bottomNav.setOnItemSelectedListener { item ->
            val currentFragment: Fragment = when (item.itemId) {
                R.id.nav_list_item -> {
                    TasksFragment()
                }

                R.id.nav_setting_item -> {
                    SettingsFragment()
                }

                else -> {
                    TasksFragment()
                }
            }
            pushFragment(currentFragment)
            true
        }
        viewBinding.bottomNav.selectedItemId = R.id.nav_list_item
        viewBinding.fabAddTask.setOnClickListener {
            showAddTaskBottomSheet()
        }
    }

    private fun showAddTaskBottomSheet() {
        val addTaskSheet = AddTaskFragment()
        addTaskSheet.show(supportFragmentManager,null)
    }

    @SuppressLint("SetTextI18n")
    private fun pushFragment(currentFragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,currentFragment)
            .commit()

    }
}