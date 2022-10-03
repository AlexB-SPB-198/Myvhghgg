package com.example.myapplication11.ui.home

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.R
import com.example.myapplication11.Task
import com.example.myapplication11.databinding.ItemTaskBinding


class TaskAdapter(private val onLongClick:(Int)->Unit ) :
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    var onClick:((Task)->Unit)?=null
    private val data = arrayListOf<Task>()
    fun addTask(task: Task) {
        data.add(0,task)
        notifyItemChanged(0)

    }
    fun addTasks(List:List<Task>){
        data.clear()
        data.addAll(List)
        notifyDataSetChanged()
    }
    fun getTask(position: Int):Task{
        return  data[position]
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(
            ItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
        if (position % 2==0){
            holder.itemView.setBackgroundColor(Color.BLACK)

        }else{
            holder.itemView.setBackgroundColor(Color.WHITE)


        }
    }

    override fun getItemCount(): Int {
        return data.size
    }


    inner class TaskViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            if(adapterPosition%2==0){
                binding.tvTitle.setTextColor(Color.WHITE)
                binding.tvDescription.setTextColor(Color.WHITE)
            }else{binding.tvTitle.setTextColor(Color.BLACK)
                binding.tvDescription.setTextColor(Color.BLACK)
            }
              /*  if(adapterPosition%2 ==0){
                itemView.setBackgroundColor(ContextCompat.getColor( context, R.color.black))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.white))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.white))
            }else{
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
                binding.tvTitle.setTextColor(ContextCompat.getColor(context,R.color.black))
                binding.tvDescription.setTextColor(ContextCompat.getColor(context,R.color.black))
            }*/

                binding.tvDescription.text = task.description
                binding.tvTitle.text = task.title
                itemView.setOnClickListener {
                    onClick?.invoke(
                        task
                    )
                }
                itemView.setOnLongClickListener {
                    onLongClick(adapterPosition)
                    false
                }
            }

    }
}