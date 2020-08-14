package com.rolandoasmat.aji.recipe_details

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.navArgs
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import javax.inject.Inject

class RecipeDetailsActivity: AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by viewModels { viewModelFactory }

    private val args: RecipeDetailsActivityArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (applicationContext as? AjiApplication)?.component()?.inject(this)
        setContentView(R.layout.activity_recipe_details)
        intent.extras?.let { bundle ->
            findNavController(R.id.nav_host_fragment).setGraph(R.navigation.recipe_details, bundle)
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel.fetchRecipeDetails(args.recipeIDArg)
    }


    override fun onSupportNavigateUp(): Boolean {
        if (!findNavController(R.id.nav_host_fragment).navigateUp()){
            // If nav controller doesn't handle Up navigation, manually press hardware Back button
            onBackPressed()
        }
        return true
    }
}
