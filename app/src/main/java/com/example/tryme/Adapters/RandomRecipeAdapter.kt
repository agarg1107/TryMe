package com.example.tryme.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Listeners.RandomRecipeListener
import com.example.tryme.Listeners.Recipe_Click_Listener
import com.example.tryme.Models.Recipe
import com.example.tryme.R
import com.squareup.picasso.Picasso

class RandomRecipeAdapter(val recipe : List<Recipe>, val recipe_click: Recipe_Click_Listener): RecyclerView.Adapter<RandomRecipeAdapter.RandomRecipeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RandomRecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context);
        val view = inflater.inflate(R.layout.random_recipe_list,parent,false);
        return RandomRecipeViewHolder(view);
    }

    override fun getItemCount(): Int {
        return recipe.size;
    }

    override fun onBindViewHolder(holder: RandomRecipeViewHolder, position: Int) {
        holder.dish_name.text = recipe[position].title;
        holder.dish_like.text = recipe[position].aggregateLikes.toString()+" Likes";
        holder.dish_serve.text = recipe[position].servings.toString()+" Person";
        holder.dish_time.text = recipe[position].readyInMinutes.toString()+" Minutes";
        val picasso = Picasso.get().load(recipe[position].image).into(holder.dish_image);
        holder.card_name.setOnClickListener ( object : View.OnClickListener{
            override fun onClick(p0: View?) {
                recipe_click.onRecipe_Clicked(recipe.get(holder.adapterPosition).id.toString());
            }

        } );
    }
    class RandomRecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var card_name = itemView.findViewById<CardView>(R.id.random_list_container);
        var dish_name = itemView.findViewById<TextView>(R.id.dish_name);
        var dish_like = itemView.findViewById<TextView>(R.id.like_dish);
        var dish_serve = itemView.findViewById<TextView>(R.id.serve_dish);
        var dish_time = itemView.findViewById<TextView>(R.id.time_dish);
        var dish_image = itemView.findViewById<ImageView>(R.id.dish_image);

    }
}
