package com.example.tryme.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Models.getInstructions
import com.example.tryme.R

class InstructionAdapter(val instruction : List<getInstructions>) : RecyclerView.Adapter<InstructionAdapter.InstructionViewHolder>() {
    class InstructionViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
//        val instruction_name = itemView.findViewById<TextView>(R.id.Instruction_name)
        val instruction_step = itemView.findViewById<RecyclerView>(R.id.Instruction_step_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.instruction_list,parent,false);
        return InstructionViewHolder(view);
    }

    override fun getItemCount(): Int {
        return instruction.size
    }

    override fun onBindViewHolder(holder: InstructionViewHolder, position: Int) {
        val context : Context = holder.itemView.context
//        holder.instruction_name.text = instruction[position].name
        holder.instruction_step.setHasFixedSize(true)
        holder.instruction_step.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val instructionStepAdapter = InstructionStepAdapter(instruction[position].steps)
        holder.instruction_step.adapter = instructionStepAdapter

    }
}