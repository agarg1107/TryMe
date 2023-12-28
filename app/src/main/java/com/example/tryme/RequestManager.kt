package com.example.tryme
import android.content.Context
import android.util.Log
import com.example.tryme.Listeners.InstructionRecipeListener
import com.example.tryme.Listeners.RandomRecipeListener
import com.example.tryme.Listeners.RecipeDetailListener
import com.example.tryme.Listeners.SimilarRecipeListener
import com.example.tryme.Models.GetSimilarRecipe
import com.example.tryme.Models.RandomRecipeApiCall
import com.example.tryme.Models.RecipeDetailResponse
import com.example.tryme.Models.getInstructions
import com.google.gson.Gson
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public class RequestManager(context: Context) {


    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.spoonacular.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    public fun getRandomRecipe(randomRecipeListener: RandomRecipeListener, tags : List<String>){
        val callRandomRecipe: CallRandomRecipe = retrofit.create(CallRandomRecipe::class.java);
        val call : Call<RandomRecipeApiCall> = callRandomRecipe.getRandom(tags,"20","c813da82546f4bb2ae76a9e1a55c65a2");
        call.enqueue(object : Callback<RandomRecipeApiCall>{
            override fun onResponse(call: Call<RandomRecipeApiCall>, response: Response<RandomRecipeApiCall>) {
                // Handle successful response
                if(!response.isSuccessful()){
                    randomRecipeListener.didError(response.message());
                    return;
                }
                if (response.body() != null) {
                    randomRecipeListener.didFetch(response.body()!!,response.message())
                };
            }
            override fun onFailure(call: Call<RandomRecipeApiCall>, t: Throwable) {
                randomRecipeListener.didError(t.message.toString());
            }
        })
    }
    public fun getRecipeDetail(recipeDetailListener: RecipeDetailListener , id : Int){
        val call_id_recipe : Call_Id_Recipe = retrofit.create(Call_Id_Recipe::class.java);
        val call : Call<RecipeDetailResponse> = call_id_recipe.getinformation(id,"c813da82546f4bb2ae76a9e1a55c65a2");
        call.enqueue(object : Callback<RecipeDetailResponse>{
            override fun onResponse(call: Call<RecipeDetailResponse>, response: Response<RecipeDetailResponse>) {
                if(!response.isSuccessful()){
                    recipeDetailListener.didError(response.message());
                    return;
                }
                if(response.body() != null){
                    recipeDetailListener.didFetch(response.body()!!,response.message())
                }
            }
            override fun onFailure(call: Call<RecipeDetailResponse>, t: Throwable) {
                recipeDetailListener.didError(t.message.toString());
            }

        })
    }
    fun getSimilarRecipe(similarRecipeListener: SimilarRecipeListener,id:Int){
        val call_similar : Call_Similar_Recipe = retrofit.create(Call_Similar_Recipe::class.java)
        val call : Call<List<GetSimilarRecipe>> = call_similar.getSimilar(id,"5","c813da82546f4bb2ae76a9e1a55c65a2")
        call.enqueue(object :Callback<List<GetSimilarRecipe>>{
            override fun onResponse(call: Call<List<GetSimilarRecipe>>, response: Response<List<GetSimilarRecipe>>) {
                if(!response.isSuccessful()){
                    similarRecipeListener.didError(response.message())
                    return
                }
                if(response.body() != null){
                    similarRecipeListener.didFetch(response.body()!!,response.message())
                }
            }
            override fun onFailure(call: Call<List<GetSimilarRecipe>>, t: Throwable) {
                similarRecipeListener.didError(t.message.toString())
            }
        })
    }
    fun getRecipeInstruction(instructionRecipeListener: InstructionRecipeListener,id:Int){
        val call_instruction : Call_Instructions = retrofit.create(Call_Instructions::class.java)
        val call : Call<List<getInstructions>> = call_instruction.getInstruction(id,"c813da82546f4bb2ae76a9e1a55c65a2")
        call.enqueue(object : Callback<List<getInstructions>>{
            override fun onResponse(call: Call<List<getInstructions>>, response: Response<List<getInstructions>>) {
                if(!response.isSuccessful()){
                    instructionRecipeListener.didError(response.message())
                    return
                }
                if(response.body() != null){
                    instructionRecipeListener.didFetch(response.body()!!,response.message())
                }

            }

            override fun onFailure(call: Call<List<getInstructions>>, t: Throwable) {
                instructionRecipeListener.didError(t.message.toString())
            }
        })
    }
    private interface CallRandomRecipe{
        @GET("recipes/random")
        fun getRandom(
            @Query("tags") tags : List<String>,
            @Query("number") number: String,
            @Query("apiKey") apiKey: String

        ): Call<RandomRecipeApiCall>
    }
    private interface Call_Id_Recipe{
        @GET("recipes/{id}/information")
        fun getinformation(
            @Path("id") id : Int,
            @Query("apiKey") apiKey: String
        ): Call<RecipeDetailResponse>
    }
    private interface Call_Similar_Recipe{
        @GET("recipes/{id}/similar")
        fun getSimilar(
            @Path("id") id: Int,
            @Query("number") number: String,
            @Query("apiKey") apiKey: String
        ): Call<List<GetSimilarRecipe>>
    }
    private interface Call_Instructions{
        @GET("recipes/{id}/analyzedInstructions")
        fun getInstruction(
            @Path("id") id:Int,
            @Query("apiKey") apiKey: String
        ): Call<List<getInstructions>>
    }
}