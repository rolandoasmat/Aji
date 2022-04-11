package com.rolandoasmat.aji.recipe_breakdown

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.model.RecipeBreakdownStep
import kotlinx.android.synthetic.main.fragment_recipe_breakdown.*
// TODO RAA add DI to this screen as well as support landscape mode
/**
 * Step by step instructions of the fragment
 */
class RecipeBreakdownFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recipe_breakdown, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        val items = (1..8).map { i ->
            RecipeBreakdownStep("", "breakdown step number $i")
        }
        breakdownViewPager?.adapter = RecipeBreakdownFragmentStateAdapter(items, this)
        TabLayoutMediator(breakdownTabLayout, breakdownViewPager) { tab, position ->
            tab.text = "Step ${(position + 1)}"
        }.attach()
    }


}