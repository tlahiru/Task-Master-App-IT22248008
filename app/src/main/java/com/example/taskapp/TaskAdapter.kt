package com.example.taskapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private var tasks :List<Task>,context: Context):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    private val db:TaskDatabaseHelper = TaskDatabaseHelper(context)


    class TaskViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val titleTextView : TextView = itemView.findViewById(R.id.titleTextView)
        val descriptionTextView : TextView = itemView.findViewById(R.id.descriptionTextView)
        val dateTextView : TextView = itemView.findViewById(R.id.dateTextView)
        val timeTextView : TextView = itemView.findViewById(R.id.timeTextView)
        val updatebtn : ImageView = itemView.findViewById(R.id.updatebutton)
        val deletebutton : ImageView = itemView.findViewById(R.id.deletebutton)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item,parent,false)
        return TaskViewHolder(view)

    }

    override fun getItemCount(): Int {
        return  tasks.size
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.descriptionTextView.text = task.description
        holder.dateTextView.text = task.date
        holder.timeTextView.text = task.time

        holder.updatebtn.setOnClickListener {
            val intent = Intent(holder.itemView.context,UpdateTaskActivity::class.java).apply{
                putExtra("task_id",task.id)
            }

            holder.itemView.context.startActivity(intent)
        }

        holder.deletebutton.setOnClickListener{
            db.deleteTast(task.id)
            refreshdata(db.getAllTasks())
            Toast.makeText(holder.itemView.context, "Task Deleted successfully", Toast.LENGTH_LONG).show()
        }


    }

    fun refreshdata(newTasks :List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}