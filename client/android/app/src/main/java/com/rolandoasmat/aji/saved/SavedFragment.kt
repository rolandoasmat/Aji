package com.rolandoasmat.aji.saved

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
import com.rolandoasmat.aji.di.ViewModelFactory
import com.rolandoasmat.aji.extensions.gone
import com.rolandoasmat.aji.extensions.visible
import com.rolandoasmat.aji.ui.RecipeSectionView
import com.rolandoasmat.aji.ui.RecipesRecyclerView
import kotlinx.android.synthetic.main.fragment_saved.*
import javax.inject.Inject

class SavedFragment : Fragment(), RecipesRecyclerView.Callbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: SavedViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        recipesRecyclerView?.setCallback(this)
    }

    private fun observeViewModel() {
        viewModel.uiModel.observe(viewLifecycleOwner, Observer {
            recipesRecyclerView?.setData(it.recipes , RecipeSectionView.SectionType.VERTICAL_COLUMN)
            if (it.recipes.isNullOrEmpty()){
                emptyStateLabel?.visible()
            } else {
                emptyStateLabel?.gone()
            }
        })
    }

    override fun onImageTapped(recipeID: String) {
        val action = SavedFragmentDirections.actionRecipeToRecipeDetails(recipeID)
        findNavController().navigate(action)
    }
}
