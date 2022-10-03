package com.example.myapplication11.ui.onBoard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.OnBoard
import com.example.myapplication11.R
import com.example.myapplication11.databinding.ItemOnBoardingPageBinding
import com.example.myapplication11.loadImage

class OnBoardAdapter(private val onClick:()->Unit):RecyclerView.Adapter <OnBoardAdapter.OnBoardViewHolder>(){

    private val arrayListBoarding = arrayListOf<OnBoard>()
    init {

        arrayListBoarding.add( OnBoard(R.raw.help,
            "Task Manager","Ваш лучший помощник"
        )
        )
        arrayListBoarding.add(  OnBoard(R.raw.times_up,
            "Task Manager","Напомнит про Ваши задачи"
        )
        )
        arrayListBoarding.add(   OnBoard(R.raw.multitasking,
            "С Task Manager","Вы успеете все")
        )

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardViewHolder {
        return OnBoardViewHolder(
            ItemOnBoardingPageBinding.inflate(LayoutInflater.from(parent.context),
                parent,false))

    }

    override fun onBindViewHolder(holder: OnBoardViewHolder, position: Int) {
        holder.bind(arrayListBoarding[position])
    }

    override fun getItemCount(): Int = arrayListBoarding.size


    inner class OnBoardViewHolder(private val binding: ItemOnBoardingPageBinding) :
        RecyclerView.ViewHolder(binding.root){
        fun bind(onBoard: OnBoard) {
            binding.skip.setOnClickListener{onClick()}
            binding.btnStart.setOnClickListener{onClick()}
            binding.skip.isVisible = adapterPosition!= arrayListBoarding.size-1
            binding.btnStart.isVisible = adapterPosition== arrayListBoarding.size-1
            binding.tvTitle.text = onBoard.title
            binding.tvDescription.text = onBoard.description
            onBoard.animation?.let { binding.imagePager.setAnimation(it) }
           /* binding.imagePager.loadImage(onBoard.image)*/
        }

    }


}


