package com.rolandoasmat.aji.ui.entrees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.mealslist.MealsListUiModel
import kotlinx.android.synthetic.main.fragment_appetizers.*

class EntreesFragment : Fragment() {

    private lateinit var viewModel: EntreesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EntreesViewModel::class.java)
        observeViewModel()
        viewModel.fetch()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_appetizers, container, false)
    }

    private fun observeViewModel() {
        viewModel.meals.observe(this, Observer {
            render(it)
        })
    }

    private fun render(mealsList: MealsListUiModel?) {
        mealsList?.let {
            appetizersListView?.setData(it)
        }
    }

}