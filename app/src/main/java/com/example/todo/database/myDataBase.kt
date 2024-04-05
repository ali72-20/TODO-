package com.example.todo.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.todo.database.dao.TasksDao
import com.example.todo.database.model.Task
import com.example.todo.ui.ui.home.MainActivity

@Database (entities = [Task::class], version = 2, exportSchema = true)
abstract class myDataBase : RoomDatabase(){

    abstract fun getDoa() : TasksDao

    companion object {
        private var database: myDataBase? = null
        private const val database_KAY = "taskDatabase"
        fun init(app:Application) {
                if (database == null) {
                    database = Room.databaseBuilder(
                        app.applicationContext,
                        myDataBase::class.java,
                        database_KAY,
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
                }
            }
        fun getInstance() : myDataBase{
            return database!!
        }
    }
}