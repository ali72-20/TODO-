package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.example.todo.R.drawable.done_task
import com.example.todo.database.myDataBase
import com.example.todo.databinding.ActivityMainBinding
import com.example.todo.fragments.AddTaskFragment
import com.example.todo.fragments.SettingsFragment
import com.example.todo.fragments.showtask.TasksFragment
import com.google.android.material.navigation.NavigationBarItemView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        initView()
        viewBinding.navAddTask.setOnClickListener{item->
            val currentFragment : Fragment = AddTaskFragment()
            supportFragmentManager.beginTransaction().replace(R.id.fragment_container,currentFragment)
                .commit()
        }
    }
    private fun initView() {
        viewBinding.bottomNav.setOnItemSelectedListener { item->
            val currentFragment : Fragment = when(item.itemId){
                R.id.nav_add_task->{
                    AddTaskFragment()
                }
                R.id.nav_list_item->{
                    TasksFragment()
                }
                R.id.nav_setting_item->{
                    SettingsFragment()
                } else ->{
                    TasksFragment()
                }
            }
            pushFragment(currentFragment)
            true
        }
        viewBinding.bottomNav.selectedItemId = R.id.nav_list_item

    }

    private fun pushFragment(currentFragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container,currentFragment)
            .commit()
    }
}