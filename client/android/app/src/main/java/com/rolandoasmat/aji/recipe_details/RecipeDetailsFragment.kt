package com.rolandoasmat.aji.recipe_details

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.di.ViewModelFactory
import com.rolandoasmat.aji.extensions.gone
import com.rolandoasmat.aji.extensions.visible
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import kotlinx.android.synthetic.main.fragment_recipe_details.loadingBar
import kotlinx.android.synthetic.main.fragment_recipe_details.pullToRefresh
import javax.inject.Inject

class RecipeDetailsFragment: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by activityViewModels { viewModelFactory }

    private val tabNames = listOf("Info", "Ingredients", "Steps")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipe_details, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setupFab()
        toolbar?.navigationIcon = ResourcesCompat.getDrawable(resources, R.drawable.ic_arrow_back_24, null)
        toolbar?.setNavigationOnClickListener {
            (activity as? AppCompatActivity)?.onSupportNavigateUp()
        }
        pullToRefresh?.setOnRefreshListener {
            viewModel.refresh()
            pullToRefresh?.isRefreshing = false
        }
        beginRecipeButton?.setOnClickListener {
            navigateToRecipeBreakdown()
        }
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loadingBar?.visible()
            } else {
                loadingBar?.gone()
            }
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let { message ->
                AlertDialog.Builder(requireContext())
                    .setTitle(R.string.error_dialog_title)
                    .setMessage(message)
                    .show()
                viewModel.errorHandled()
            }
        }
        viewModel.details.observe(viewLifecycleOwner) { uiModel ->
            uiModel?.let { data ->
                (activity as? AppCompatActivity)?.supportActionBar?.let {
                    it.title = data.title
                }
                collapsingToolbar?.title = data.title
                data.posterURL?.let { url ->
                    ImageLoader.load(url, poster)
                }
                setupViewPager()
                fabIcon?.visibility = View.VISIBLE
            }
        }
        viewModel.isFavoriteRecipe.observe(viewLifecycleOwner) {
            fabIcon?.isSelected = it == true
        }
    }

    private fun setupViewPager() {
        viewPager?.adapter = RecipeDetailsViewPagerAdapter(this)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = tabNames[position]
        }.attach()
        viewPager?.isUserInputEnabled = false
    }

    private fun setupFab() {
        fabIcon?.setOnClickListener {
            viewModel.fabClicked()
        }
    }

    private fun navigateToRecipeBreakdown() {
        val action = RecipeDetailsFragmentDirections.recipeDetailsFragmentToRecipeBreakdownFragment("123")
        findNavController().navigate(action)
    }

}