package com.example.taskapp

import java.util.*

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: String
)