package com.rolandoasmat.aji.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.observe
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.di.ViewModelFactory
import com.rolandoasmat.aji.extensions.visible
import com.rolandoasmat.aji.recipe_details.RecipeDetailsViewModel
import kotlinx.android.synthetic.main.fragment_info.*
import javax.inject.Inject

class InfoFragment: Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: RecipeDetailsViewModel by activityViewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.details.observe(viewLifecycleOwner) { uiModel ->
            uiModel?.let { data ->
                description?.text = data.description
                durationLabel?.text = data.cookingTime
                servingsLabel?.text = data.servingSize
                divider?.visible()
            }
        }
    }
}