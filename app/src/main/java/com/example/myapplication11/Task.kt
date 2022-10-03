package com.example.myapplication11

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id:Long?=null,
    var title:String,
    var description:String
): Serializable
