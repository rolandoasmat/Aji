package com.rolandoasmat.aji.recipe_details

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.rolandoasmat.aji.R

class RecipeDetailsActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_details)
        intent.extras?.let { bundle ->
            findNavController(R.id.nav_host_fragment).setGraph(R.navigation.recipe_details, bundle)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    override fun onSupportNavigateUp(): Boolean {
        if (!findNavController(R.id.nav_host_fragment).navigateUp()){
            // If nav controller doesn't handle Up navigation, manually press hardware Back button
            onBackPressed()
        }
        return true
    }
}
