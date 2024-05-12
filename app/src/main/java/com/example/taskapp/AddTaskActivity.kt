package com.example.taskapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddTaskActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var dateEditText: EditText
    private lateinit var timeEditText: EditText
    private lateinit var calendar: Calendar
    private lateinit var db: TaskDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.addtask_activity)

        titleEditText = findViewById(R.id.titleEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        dateEditText = findViewById(R.id.dateEditText)
        timeEditText = findViewById(R.id.timeEditText)
        calendar = Calendar.getInstance()

        db = TaskDatabaseHelper(this)

        val saveBtn: Button = findViewById(R.id.savebtn)
        saveBtn.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()
            val date = dateEditText.text.toString()
            val time = timeEditText.text.toString()

            val task = Task(0, title, description, date, time)
            db.insertTask(task)
            finish()
            Toast.makeText(this, "Task saved successfully", Toast.LENGTH_LONG).show()
        }
    }

    fun showDatePickerDialog(view: View) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // Set the selected date to the EditText
            dateEditText.setText("$dayOfMonth/${month + 1}/$year")
        }, year, month, day)
        datePickerDialog.show()
    }

    fun showTimePickerDialog(view: View) {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            // Set the selected time to the EditText
            timeEditText.setText("$hourOfDay:$minute")
        }, hour, minute, true)
        timePickerDialog.show()
    }
}
