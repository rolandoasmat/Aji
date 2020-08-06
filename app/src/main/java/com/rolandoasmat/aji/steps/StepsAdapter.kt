package com.rolandoasmat.aji.steps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.R
import kotlinx.android.synthetic.main.item_step.view.*

class StepsAdapter: RecyclerView.Adapter<StepsAdapter.ViewHolder>() {

    private var ingredients: List<String> = emptyList()

    fun setData(data: List<String>) {
        this.ingredients = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.item_step, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val label = itemView.stepLabel

        fun bind(ingredient: String) {
            label.text = ingredient
        }
    }
}