package com.example.myapplication11.ui

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication11.Task
import com.example.myapplication11.TaskDao

@Database(entities = [Task::class],version = 1)
abstract class TaskDataBase:RoomDatabase() {
    abstract fun dao () : TaskDao

    }
