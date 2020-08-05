package com.rolandoasmat.aji.recipe_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import javax.inject.Inject

class RecipeDetailsFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by viewModels { viewModelFactory }

    val args: RecipeDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
        viewModel.fetchRecipeDetails(args.recipeIDArg)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupViewPager()
    }

    private fun setupViewPager() {
        val adapter = RecipeDetailsViewPagerAdapter(this)
        viewPager?.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.details.observe(viewLifecycleOwner) {
            Log.v("RAA", it.toString())
            (activity as? AppCompatActivity)?.supportActionBar?.title = it.title
        }
    }

}