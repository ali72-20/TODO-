package com.example.todo.database

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TasksDao
import com.example.todo.ui.ui.home.MainActivity

abstract class myDataBase : RoomDatabase(){

    abstract fun getDoa() : TasksDao

    companion object{
        private var db : myDataBase? = null
        const val databaseName = "tasks database"
        fun init(context: Application){
            if(db != null) {
                db = Room.databaseBuilder(
                    context.applicationContext,
                    myDataBase::class.java, databaseName
                ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
        }
         fun getInstance() : myDataBase{
             return db!!
         }
    }
}