package com.rolandoasmat.aji.mealslist

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Adapter for showing a list of meals
 */
class MealsListAdapter : RecyclerView.Adapter<MealsListAdapter.ViewHolder>() {

    private var data: MealsListUiModel? = null

    fun setData(data: MealsListUiModel) {
        this.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int = data?.meals?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.meals?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        fun bind(data: MealsListItemUiModel) {

        }

    }

}