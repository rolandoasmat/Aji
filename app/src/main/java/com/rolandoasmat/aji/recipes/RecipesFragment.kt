package com.rolandoasmat.aji.recipes

import android.os.Bundle
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
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.fragment_recipes.*
import kotlinx.android.synthetic.main.item_meal.*
import javax.inject.Inject

class RecipesFragment : Fragment(), MealListItemCallbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipesViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
        viewModel.fetch()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        dinnerRecipesListView?.setCallback(this)
    }

    private fun observeViewModel() {
        viewModel.featured.observe(viewLifecycleOwner, Observer {
            it?.let {
                mealTitle?.text = it.mealTitle
                ImageLoader.load(it.backdropURL, mealBackdrop)
            }
        })
        viewModel.breakfast.observe(viewLifecycleOwner, Observer {
            it?.let { breakfastRecipesListView?.setData(it) }
        })
        viewModel.dinner.observe(viewLifecycleOwner, Observer {
            it?.let { dinnerRecipesListView?.setData(it) }
        })
        viewModel.appetizers.observe(viewLifecycleOwner, Observer {
            it?.let { appetizerRecipesListView?.setData(it) }
        })
        viewModel.desserts.observe(viewLifecycleOwner, Observer {
            it?.let { dessertsRecipesListView?.setData(it) }
        })
        viewModel.drinks.observe(viewLifecycleOwner, Observer {
            it?.let { drinksRecipesListView?.setData(it) }
        })
    }

    override fun onImageTapped(recipeID: Int) {
        val action = RecipesFragmentDirections.actionRecipeToRecipeDetails(recipeID)
        findNavController().navigate(action)
    }

}