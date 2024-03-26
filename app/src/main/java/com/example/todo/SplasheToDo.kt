package com.example.todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.todo.databinding.ActivitySplasheToDoBinding

class SplasheToDo : AppCompatActivity() {
    lateinit var viewBinding : ActivitySplasheToDoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySplasheToDoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        startApp()
    }

    private fun startApp() {
        Handler(mainLooper).postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        },2000)
    }
}