package com.example.tryme.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Models.Equipment
import com.example.tryme.Models.Ingredient
import com.example.tryme.R
import com.squareup.picasso.Picasso

class InstructionStepEquipmentAdapter(val equipment : List<Equipment>):RecyclerView.Adapter<InstructionStepEquipmentAdapter.InstructionStepEquipmentViewHolder>() {
    class InstructionStepEquipmentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_name = itemView.findViewById<TextView>(R.id.instruction_step_item_name)
        val item_image = itemView.findViewById<ImageView>(R.id.instruction_step_item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionStepEquipmentViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.instruction_list_step_item,parent,false)
        return InstructionStepEquipmentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return equipment.size
    }

    override fun onBindViewHolder(holder: InstructionStepEquipmentViewHolder, position: Int) {
        holder.item_name.text = equipment[position].name
        holder.item_name.isSelected = true

        Picasso.get().load("https://spoonacular.com/cdn/equipment_250x250/"+equipment[position].image).into(holder.item_image)

    }


}