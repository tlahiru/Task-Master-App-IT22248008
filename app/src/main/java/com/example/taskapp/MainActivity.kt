package com.example.taskapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val logo: ImageView =findViewById<ImageView>(R.id.logo)

        logo.setOnClickListener{
            val intent = Intent(this,DisplayActivity::class.java)
            startActivity(intent)
        }
    }
}