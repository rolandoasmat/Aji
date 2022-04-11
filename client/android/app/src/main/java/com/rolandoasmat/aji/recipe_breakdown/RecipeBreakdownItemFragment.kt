package com.rolandoasmat.aji.recipe_breakdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.model.RecipeBreakdownStep
import kotlinx.android.synthetic.main.fragment_recipe_breakdown_item.*

class RecipeBreakdownItemFragment(
    private val data: RecipeBreakdownStep
    ): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe_breakdown_item, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stepDescription?.text = data.stepDescription
    }
}
