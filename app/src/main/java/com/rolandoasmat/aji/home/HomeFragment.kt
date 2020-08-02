package com.rolandoasmat.aji.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rolandoasmat.aji.AjiApplication
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.ViewModelFactory
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : Fragment() {

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
        observeViewModel()
        viewModel.fetch()
    }

    private fun observeViewModel() {
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


}