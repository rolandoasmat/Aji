package com.rolandoasmat.aji.recipes

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
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
    }

    private fun observeViewModel() {
        viewModel.breakfast.observe(viewLifecycleOwner, Observer {
            it?.let {
                it.sections.forEach { section ->
                    val sectionView = RecipeSectionView(requireContext(), section, this)
                    sectionsLinearLayout?.addView(sectionView)
                }
            }
        })
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loadingBar?.visibility = View.VISIBLE
            } else {
                loadingBar?.visibility = View.GONE
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { message ->
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.error_dialog_title)
                    .setMessage(message)
                    .show()
                viewModel.errorHandled()
            }
        }
    }

    override fun onImageTapped(recipeID: String) {
        val action = RecipesFragmentDirections.actionRecipeToRecipeDetails(recipeID)
        findNavController().navigate(action)
    }

}