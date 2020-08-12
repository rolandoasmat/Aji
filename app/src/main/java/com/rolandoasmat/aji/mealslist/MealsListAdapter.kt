package com.rolandoasmat.aji.mealslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.recipes.RecipesUIModel
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.item_meal.view.*

/**
 * Adapter for showing a list of meals
 */

class MealsListAdapter(private val callbacks: MealListItemCallbacks) : RecyclerView.Adapter<MealsListAdapter.ViewHolder>() {

    private var data: List<RecipesUIModel.Entry>? = null

    fun setData(data: List<RecipesUIModel.Entry>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if (data?.get(position)?.thumbnailURL == null) {
            R.layout.item_recipe_no_photo
        } else {
            R.layout.item_meal_narrow
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.getOrNull(position)?.let {
            holder.bind(it)
            holder.setCallbacks(position, callbacks)
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val mealBackdrop: ImageView? = view.mealBackdrop
        private val mealTitle: TextView? = view.mealTitle

        fun bind(data: RecipesUIModel.Entry) {
            mealBackdrop?.let { imageView ->
                data.thumbnailURL?.let { backdropURL ->
                    ImageLoader.load(backdropURL, imageView)
                }
            }
            mealTitle?.text = data.title
        }

        fun setCallbacks(position: Int, callbacks: MealListItemCallbacks ) {
            mealTitle?.setOnClickListener {
                data?.getOrNull(position)?.let { uiModel ->
                    callbacks.onImageTapped(uiModel.recipeID)
                }
            }
        }
    }

}