package com.example.taskapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DisplayActivity:AppCompatActivity() {

    private lateinit var db : TaskDatabaseHelper
    private lateinit var taskAdapter : TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display_activity)

        val addbtn :FloatingActionButton = findViewById<FloatingActionButton>(R.id.addbutton)

        db = TaskDatabaseHelper(this)
        taskAdapter = TaskAdapter(db.getAllTasks(),this)

        val displaytaskView : RecyclerView = findViewById(R.id.taskrecyclerview)
        displaytaskView.layoutManager = LinearLayoutManager(this)

        displaytaskView.adapter = taskAdapter;


        addbtn.setOnClickListener{
            val intent = Intent(this,AddTaskActivity::class.java)
            startActivity(intent)
        }


    }

    override   fun onResume() {
        super.onResume()
        taskAdapter. refreshdata(db.getAllTasks())
    }
}