package com.rolandoasmat.aji.recipe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.google.android.material.tabs.TabLayoutMediator
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.fragment_recipe_details.*
import javax.inject.Inject

class RecipeDetailsFragment: Fragment() {

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by activityViewModels { viewModelFactory }

    private val tabNames = listOf("Ingredients", "Steps")

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
    }

    private fun observeViewModel() {
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loadingBar?.visibility = View.VISIBLE
            } else {
                loadingBar?.visibility = View.GONE
            }
        }
        viewModel.details.observe(viewLifecycleOwner) { uiModel ->
            (activity as? AppCompatActivity)?.supportActionBar?.let {
                it.title = uiModel.title
            }
            uiModel.posterURL?.let { url ->
                ImageLoader.load(url, poster)
            }
            description?.text = uiModel.description
            setupViewPager()
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
    }

    private fun setupFab() {
        fabIcon?.setOnClickListener {
            viewModel.fabClicked()
        }
    }

}