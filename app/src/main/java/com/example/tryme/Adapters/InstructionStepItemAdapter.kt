package com.example.tryme.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Models.Ingredient
import com.example.tryme.R
import com.squareup.picasso.Picasso

class InstructionStepItemAdapter(val ingrediants : List<Ingredient>):RecyclerView.Adapter<InstructionStepItemAdapter.InstructionStepViewHolder>() {
    class InstructionStepViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val item_name = itemView.findViewById<TextView>(R.id.instruction_step_item_name)
        val item_image = itemView.findViewById<ImageView>(R.id.instruction_step_item_image)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionStepViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = inflate.inflate(R.layout.instruction_list_step_item,parent,false)
        return InstructionStepViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingrediants.size
    }

    override fun onBindViewHolder(holder: InstructionStepViewHolder, position: Int) {
        holder.item_name.text = ingrediants[position].name
        holder.item_name.isSelected = true

        Picasso.get().load("https://spoonacular.com/cdn/ingredients_250x250/"+ingrediants[position].image).into(holder.item_image)

    }


}