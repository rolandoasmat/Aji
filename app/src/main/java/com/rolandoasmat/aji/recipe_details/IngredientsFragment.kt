package com.rolandoasmat.aji.recipe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.R
import kotlinx.android.synthetic.main.fragment_ingredients.*

class IngredientsFragment : Fragment() {

    private val adapter: IngredientsAdapter = IngredientsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientsRecyclerView?.adapter = adapter
        ingredientsRecyclerView?.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    fun setData(data: List<String>) {
        adapter.setData(data)
    }

}