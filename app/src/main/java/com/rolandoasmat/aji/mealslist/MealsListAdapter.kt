package com.rolandoasmat.aji.mealslist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.item_meal.view.*

/**
 * Adapter for showing a list of meals
 */
private typealias MealsList = List<MealsListItemUiModel>

class MealsListAdapter(private val callbacks: MealListItemCallbacks) : RecyclerView.Adapter<MealsListAdapter.ViewHolder>() {

    private var data: MealsList? = null

    fun setData(data: MealsList) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val layoutID = R.layout.item_meal
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutID, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.getOrNull(position)?.let {
            holder.bind(it)
            holder.setCallbacks(position, callbacks)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val mealBackdrop: ImageView? = view.mealBackdrop
        private val mealTitle: TextView? = view.mealTitle

        fun bind(data: MealsListItemUiModel) {
            mealBackdrop?.let {
                ImageLoader.load(data.backdropURL, it)
            }
            mealTitle?.text = data.mealTitle
        }

        fun setCallbacks(position: Int, callbacks: MealListItemCallbacks ) {
            mealBackdrop?.setOnClickListener {
                callbacks.onImageTapped(position)
            }
        }
    }

}