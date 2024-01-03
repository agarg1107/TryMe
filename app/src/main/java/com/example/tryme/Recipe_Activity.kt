package com.example.tryme

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.tryme.Adapters.IngrediantsAdapter
import com.example.tryme.Adapters.InstructionAdapter
import com.example.tryme.Adapters.SimilarRecipeAdapter
import com.example.tryme.Listeners.InstructionRecipeListener
import com.example.tryme.Listeners.RecipeDetailListener
import com.example.tryme.Listeners.Recipe_Click_Listener
import com.example.tryme.Listeners.SimilarRecipeListener
import com.example.tryme.Models.GetSimilarRecipe
import com.example.tryme.Models.RecipeDetailResponse
import com.example.tryme.Models.getInstructions
import com.squareup.picasso.Picasso
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class Recipe_Activity : AppCompatActivity() {
    var id : Int? = null
    private lateinit var Recipe_detail_meal_name : TextView
    private lateinit var Recipe_detail_meal_source : TextView
    private lateinit var Recipe_detail_meal_image : ImageView
    private lateinit var placeholder : TextView
    private lateinit var Recipe_detail_meal_similar : TextView
    private lateinit var Recipe_detail_meal_ingredients_view : RecyclerView
    private lateinit var Recipe_similar_recipe : RecyclerView
    private lateinit var Recipe_Instruction_View : RecyclerView
    private lateinit var Recipe_detail_meal_ingredients : TextView
    private lateinit var requestManager: RequestManager
    private lateinit var ingrediantsAdapter: IngrediantsAdapter
    private lateinit var Recipe_detail_meal_summery :TextView
    private lateinit var similarRecipeAdapter: SimilarRecipeAdapter
    private lateinit var instructionAdapter: InstructionAdapter

    private var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)
        findView()
        visible_Gone()
        id = Integer.parseInt(intent.getStringExtra("id"));
        request_Manager(id!!);
        progress_dilog()
    }
    fun removeHtmlTags(input: String): String {
        return android.text.Html.fromHtml(input, android.text.Html.FROM_HTML_MODE_LEGACY).toString()
    }

    val recipeDetailListener = object : RecipeDetailListener{
        override fun didFetch(recipeDetailResponse: RecipeDetailResponse, message: String) {
            progressDialog?.dismiss()
            Recipe_detail_meal_name.text = recipeDetailResponse.title
            Recipe_detail_meal_source.text = recipeDetailResponse.sourceName
            Picasso.get().load(recipeDetailResponse.image).into(Recipe_detail_meal_image)
            Recipe_detail_meal_summery.text = removeHtmlTags(recipeDetailResponse.summary)
            Recipe_detail_meal_ingredients_view.setHasFixedSize(true)
            visible_visible()
            Recipe_detail_meal_ingredients_view.layoutManager = LinearLayoutManager(this@Recipe_Activity,LinearLayoutManager.HORIZONTAL,false)
//            Recipe_detail_meal_ingredients_view.layoutManager = GridLayoutManager(this@Recipe_Activity,3);
            ingrediantsAdapter = IngrediantsAdapter(recipeDetailResponse.extendedIngredients)
            Recipe_detail_meal_ingredients_view.adapter = ingrediantsAdapter
        }
        override fun didError(message: String) {
            Toast.makeText(this@Recipe_Activity, message, Toast.LENGTH_SHORT).show()
        }

    }
    val similarRecipeListener = object :SimilarRecipeListener{
        override fun didFetch(getSimilarRecipe: List<GetSimilarRecipe>, message: String) {
            Recipe_similar_recipe.setHasFixedSize(true)
            Recipe_similar_recipe.layoutManager = LinearLayoutManager(this@Recipe_Activity,LinearLayoutManager.HORIZONTAL,false)
            similarRecipeAdapter = SimilarRecipeAdapter(getSimilarRecipe,recipeClickListener);
            Recipe_similar_recipe.adapter = similarRecipeAdapter
        }

        override fun didError(message: String) {
            Toast.makeText(this@Recipe_Activity, message, Toast.LENGTH_SHORT).show()
        }

    }
    val instructionRecipeListener = object : InstructionRecipeListener{
        override fun didFetch(getInstructions: List<getInstructions>, message: String) {
            Recipe_Instruction_View.setHasFixedSize(true)
            Recipe_Instruction_View.layoutManager = LinearLayoutManager(this@Recipe_Activity,LinearLayoutManager.VERTICAL,false)
            val instructionAdapter = InstructionAdapter(getInstructions)
            Recipe_Instruction_View.adapter = instructionAdapter
        }

        override fun didError(message: String) {
            TODO("Not yet implemented")
        }

    }
    private final val recipeClickListener = object : Recipe_Click_Listener{
        override fun onRecipe_Clicked(id: String) {
            val intent = Intent(this@Recipe_Activity, Recipe_Activity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }

    fun findView(){
        Recipe_detail_meal_image = findViewById(R.id.Recipe_detail_meal_image)
        Recipe_detail_meal_ingredients_view = findViewById(R.id.Recipe_detail_meal_ingredients_view)
        Recipe_detail_meal_source = findViewById(R.id.Recipe_detail_meal_source)
        Recipe_detail_meal_name = findViewById(R.id.Recipe_detail_meal_name)
        Recipe_detail_meal_summery = findViewById(R.id.Recipe_detail_meal_summery)
        placeholder = findViewById(R.id.Placeholder)
        Recipe_detail_meal_ingredients = findViewById(R.id.Recipe_detail_meal_ingredients)
        Recipe_similar_recipe = findViewById(R.id.Recipe_similar_recipe)
        Recipe_detail_meal_similar=  findViewById(R.id.Recipe_detail_meal_similar)
        Recipe_Instruction_View = findViewById(R.id.Recipe_Instruction_view)
    }
    fun visible_Gone(){
        Recipe_detail_meal_similar.visibility = View.GONE
        Recipe_detail_meal_name.visibility = View.GONE
        Recipe_detail_meal_source.visibility = View.GONE
        placeholder.visibility = View.GONE
        Recipe_detail_meal_image.visibility = View.GONE
        Recipe_detail_meal_summery.visibility = View.GONE
        Recipe_detail_meal_ingredients_view.visibility = View.GONE
        Recipe_detail_meal_ingredients.visibility = View.GONE
    }
    fun visible_visible(){
        Recipe_detail_meal_name.visibility = View.VISIBLE
        Recipe_detail_meal_source.visibility = View.VISIBLE
        Recipe_detail_meal_summery.visibility = View.VISIBLE
        Recipe_detail_meal_similar.visibility = View.VISIBLE
        Recipe_detail_meal_image.visibility = View.VISIBLE
        Recipe_detail_meal_ingredients.visibility = View.VISIBLE
        placeholder.visibility = View.VISIBLE
        Recipe_detail_meal_ingredients_view.visibility = View.VISIBLE
    }
    fun request_Manager(id : Int){
        requestManager = RequestManager(this)
        requestManager.getRecipeDetail(recipeDetailListener, id);
        requestManager.getSimilarRecipe(similarRecipeListener,id)
        requestManager.getRecipeInstruction(instructionRecipeListener,id)
    }
    fun progress_dilog(){
        progressDialog = ProgressDialog(this)
        progressDialog?.setMessage("Loading...")
        progressDialog?.setCancelable(false)
        progressDialog?.show()
    }
}