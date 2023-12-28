package com.example.tryme.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Models.ExtendedIngredient
import com.example.tryme.R
import com.squareup.picasso.Picasso

class IngrediantsAdapter (val ingredient: List<ExtendedIngredient>): RecyclerView.Adapter<IngrediantsAdapter.IngrediantViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngrediantViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.meal_ingeridents_list,parent,false);
        return IngrediantViewHolder(view);
    }

    override fun getItemCount(): Int {
        return ingredient.size;
    }

    override fun onBindViewHolder(holder: IngrediantViewHolder, position: Int) {
        holder.ingrediantname.text = ingredient[position].name
        holder.ingrediantname.isSelected = true
        holder.ingrediantQuantity.text = ingredient[position].original
        holder.ingrediantQuantity.isSelected = true
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+ingredient[position].image).into(holder.ingrediantimage)
    }

    class IngrediantViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var ingrediantname = itemView.findViewById<TextView>(R.id.meal_ingeridant_name)

        var ingrediantQuantity = itemView.findViewById<TextView>(R.id.meal_ingeridant_quantity)
        var ingrediantimage = itemView.findViewById<ImageView>(R.id.meal_ingeridant_image)
    }
}
