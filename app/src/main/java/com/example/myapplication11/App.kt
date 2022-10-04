package com.example.myapplication11

import android.app.Application
import androidx.room.Room
import com.example.myapplication11.ui.TaskDataBase


class App:Application() {
    override fun onCreate() {
        super.onCreate()
        db=Room.databaseBuilder(this,TaskDataBase::class.java,"database").
        allowMainThreadQueries().build()
    }
    companion object{
        public lateinit var db:TaskDataBase
    }
}