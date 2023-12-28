package com.example.tryme.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tryme.Models.Step
import com.example.tryme.R

class InstructionStepAdapter(val step : List<Step>) : RecyclerView.Adapter<InstructionStepAdapter.instructionStapViewHolder>() {

    class instructionStapViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val instruction_step_number = itemView.findViewById<TextView>(R.id.instruction_step_number)
        val instruction_step_name = itemView.findViewById<TextView>(R.id.instruction_step_title)
        val equipment_view = itemView.findViewById<RecyclerView>(R.id.instruction_equipment_view)
        val ingredient_view = itemView.findViewById<RecyclerView>(R.id.instruction_ingridents_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): instructionStapViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.instruction_list_step,parent,false);
        return instructionStapViewHolder(view);
    }

    override fun getItemCount(): Int {
        return step.size
    }

    override fun onBindViewHolder(holder: instructionStapViewHolder, position: Int) {
        val context: Context = holder.itemView.context
        holder.instruction_step_number.text = step[position].number.toString()
        holder.instruction_step_name.text = step[position].step

        holder.ingredient_view.setHasFixedSize(true)
        holder.ingredient_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val instructionStepItemAdapter  = InstructionStepItemAdapter(step[position].ingredients)
        holder.ingredient_view.adapter = instructionStepItemAdapter

        holder.equipment_view.setHasFixedSize(true)
        holder.equipment_view.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        val instructionStepEquipmentAdapter = InstructionStepEquipmentAdapter(step[position].equipment)
        holder.equipment_view.adapter = instructionStepEquipmentAdapter
    }
}