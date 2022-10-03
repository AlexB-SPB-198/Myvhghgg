package com.example.myapplication11

import java.io.Serializable

data class OnBoard(
    val animation:Int?=null,
    val title:String?=null,
    val description:String?=null,
) : Serializable
