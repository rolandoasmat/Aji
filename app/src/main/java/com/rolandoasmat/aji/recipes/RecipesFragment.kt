package com.rolandoasmat.aji.recipes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import com.rolandoasmat.aji.mealslist.MealListItemCallbacks
import kotlinx.android.synthetic.main.fragment_recipes.*
import javax.inject.Inject

class RecipesFragment : Fragment(), MealListItemCallbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
        observeViewModel()
//        recipesListView?.setCallback(this)
    }

    private fun observeViewModel() {
        viewModel.breakfast.observe(viewLifecycleOwner, Observer {
            it?.let {
                Log.v("RAA", it.toString())
                it.sections.forEach { section ->
                    val sectionView = RecipeSectionView(requireContext(), section)
                    sectionsLinearLayout.addView(sectionView)
                }
            }
        })
    }

    override fun onImageTapped(recipeID: String) {
        val action = RecipesFragmentDirections.actionRecipeToRecipeDetails(recipeID)
        findNavController().navigate(action)
    }

}