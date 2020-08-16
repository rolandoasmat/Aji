package com.rolandoasmat.aji.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import com.rolandoasmat.aji.recipe_details.RecipeDetailsViewModel
import kotlinx.android.synthetic.main.fragment_ingredients.*
import javax.inject.Inject

class IngredientsFragment: Fragment() {

    private val adapter = IngredientsAdapter()

    @Inject lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by activityViewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientsRecyclerView?.adapter = adapter
        ingredientsRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.details.observe(viewLifecycleOwner) { uiModel ->
            uiModel?.let {
                adapter.setData(it.ingredients)
            }
        }
    }

}