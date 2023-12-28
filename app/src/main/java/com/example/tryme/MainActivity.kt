package com.example.tryme

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar

import androidx.appcompat.widget.SearchView
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tryme.Adapters.RandomRecipeAdapter
import com.example.tryme.Listeners.RandomRecipeListener
import com.example.tryme.Listeners.Recipe_Click_Listener
import com.example.tryme.Models.RandomRecipeApiCall

class MainActivity : AppCompatActivity() {
    private lateinit var requestManager: RequestManager
    private lateinit var searchView: SearchView
    private lateinit var spinner : Spinner
    private lateinit var recyclerView : RecyclerView
    private lateinit var randomRecipeAdapter: RandomRecipeAdapter
    val tag = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        searchView = findViewById(R.id.searcch_dish_home)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                tag.clear()
                tag.add(p0.toString().lowercase())
                requestManager.getRandomRecipe(randomResponseListener, tag)
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false;
            }

        });
        spinner = findViewById(R.id.spinner_for_home)
        val arrayAdapter = ArrayAdapter.createFromResource(this,R.array.tags,R.layout.spinner_text);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_inner_text);
        spinner.adapter= arrayAdapter
        spinner.onItemSelectedListener = spinner_selected_listener
        requestManager = RequestManager(this)

    }

    val randomResponseListener = object : RandomRecipeListener {
        override fun didFetch(randomRecipeApiCall: RandomRecipeApiCall, message: String) {
            recyclerView = findViewById(R.id.random_recycle);
            recyclerView.setHasFixedSize(true);
            recyclerView.layoutManager = GridLayoutManager(this@MainActivity,1);
            randomRecipeAdapter = RandomRecipeAdapter(randomRecipeApiCall.recipes,recipe_click_lis)
            recyclerView.adapter = randomRecipeAdapter;
        }

        override fun didError(message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    };

    val spinner_selected_listener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            tag.clear()
            if (p0 != null) {
                tag.add(p0.selectedItem.toString().lowercase())
            }
            requestManager.getRandomRecipe(randomResponseListener, tag)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
            // Handle case when nothing is selected
        }
    }
    val recipe_click_lis = object : Recipe_Click_Listener{
        override fun onRecipe_Clicked(id: String) {
//            Toast.makeText(this@MainActivity,id,Toast.LENGTH_SHORT).show()
            val intent = Intent(this@MainActivity, Recipe_Activity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }

}