package com.example.tryme.Adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Listeners.Recipe_Click_Listener
import com.example.tryme.Models.GetSimilarRecipe
import com.example.tryme.R
import com.squareup.picasso.Picasso

class SimilarRecipeAdapter(val similarRecipe: List<GetSimilarRecipe> , val recipeClickListener: Recipe_Click_Listener) : RecyclerView.Adapter<SimilarRecipeAdapter.similarRecipeViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): similarRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.similar_recipe_view,parent,false);
        return SimilarRecipeAdapter.similarRecipeViewHolder(view)
    }

    override fun getItemCount(): Int {
        return similarRecipe.size
    }

    override fun onBindViewHolder(holder: similarRecipeViewHolder, position: Int) {
        holder.similarRecipeName.text = similarRecipe[position].title
        holder.similarRecipeName.isSelected = true
        holder.similarRecipeServe.text = similarRecipe[position].servings.toString()+" Persons"
        holder.similarRecipeServe.isSelected = true
        Picasso.get().load("https://spoonacular.com/recipeImages/"+similarRecipe[position].id+"-556x370."+similarRecipe[position].imageType).into(holder.similarRecipeImage)
        holder.similarRecipeCard.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                recipeClickListener.onRecipe_Clicked(similarRecipe.get(holder.adapterPosition).id.toString());
            }

        })
    }

    class similarRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var similarRecipeName = itemView.findViewById<TextView>(R.id.simlar_recipe_name)
        var similarRecipeServe = itemView.findViewById<TextView>(R.id.similar_recipe_serve)
        var similarRecipeImage = itemView.findViewById<ImageView>(R.id.similar_recipe_image)
        var similarRecipeCard = itemView.findViewById<CardView>(R.id.similar_recipe_cardview)
    }
}