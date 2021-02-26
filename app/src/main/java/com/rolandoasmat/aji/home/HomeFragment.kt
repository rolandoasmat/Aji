package com.rolandoasmat.aji.home

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
import com.rolandoasmat.aji.di.ViewModelFactory
import com.rolandoasmat.aji.extensions.gone
import com.rolandoasmat.aji.extensions.visible
import com.rolandoasmat.aji.ui.RecipesRecyclerView
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment(), RecipesRecyclerView.Callbacks {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel: HomeViewModel by viewModels { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? AjiApplication)?.component()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetch()
        observeViewModel()
        pullToRefresh?.setOnRefreshListener {
            viewModel.refresh()
            pullToRefresh?.isRefreshing = false
        }
    }

    private fun observeViewModel() {
        viewModel.uiModel.observe(viewLifecycleOwner, Observer {
            it?.let {
                recipesGridView?.setUiModel(it.gridUiModel)
            }
        })
        viewModel.loading.observe(viewLifecycleOwner) {
            if (it) {
                loadingBar?.visible()
            } else {
                loadingBar?.gone()
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
        val action = HomeFragmentDirections.actionRecipeToRecipeDetails(recipeID)
        findNavController().navigate(action)
    }

}