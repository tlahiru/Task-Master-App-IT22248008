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

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var db : TaskDatabaseHelper
    private lateinit var updateTitlrEditText : EditText
    private lateinit var updateDescriptionEditText:EditText
    private lateinit var   updateDateEditText :EditText
    private lateinit var  updateTimeEditText :EditText
    private  var taskId :Int= -1
    private lateinit var calendar: Calendar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.update_task)

        updateTitlrEditText = findViewById(R.id.updateTitleEditText)
        updateDescriptionEditText = findViewById(R.id.updateDescriptionEditText)
        updateDateEditText= findViewById(R.id.updateDateEditText)
        updateTimeEditText= findViewById(R.id.updateTimeEditText)
        calendar = Calendar.getInstance()


        db = TaskDatabaseHelper(this)

        taskId = intent.getIntExtra("task_id",-1)
        if (taskId == -1){
            finish()
            return
        }
        val task = db.getTaskById(taskId)
        updateTitlrEditText.setText(task.title)
        updateDescriptionEditText.setText(task.description)
        updateDateEditText.setText(task.date)
        updateTimeEditText.setText(task.time)

        val updatebtn:Button = findViewById(R.id.updatebtn)
        updatebtn.setOnClickListener {
            val newTitle = updateTitlrEditText.text.toString()
            val newDescription = updateDescriptionEditText.text.toString()
            val newDate = updateDateEditText.text.toString()
            val newTime = updateTimeEditText.text.toString()
            val updateTask = Task(taskId,newTitle,newDescription,newDate,newTime)
            db.updateTask(updateTask)
            finish()
            Toast.makeText(this, "Task Updated successfully", Toast.LENGTH_LONG).show()

        }


    }
    fun showDatePickerDialog(view: View) {
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(this, { _, year, month, dayOfMonth ->
            // Set the selected date to the EditText
            updateDateEditText.setText("$dayOfMonth/${month + 1}/$year")
        }, year, month, day)
        datePickerDialog.show()
    }

    fun showTimePickerDialog(view: View) {
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(this, { _, hourOfDay, minute ->
            // Set the selected time to the EditText
            updateTimeEditText.setText("$hourOfDay:$minute")
        }, hour, minute, true)
        timePickerDialog.show()
    }

}