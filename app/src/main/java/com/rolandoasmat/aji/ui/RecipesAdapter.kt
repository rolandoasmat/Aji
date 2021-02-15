package com.rolandoasmat.aji.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rolandoasmat.aji.R
import com.rolandoasmat.aji.services.ImageLoader
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    private var callbacks: RecipesRecyclerView.Callbacks? = null
    private var data: List<RecipeSectionViewUiModel.Entry>? = null
    private var sectionType: RecipeSectionView.SectionType? = null

    fun setData(data: List<RecipeSectionViewUiModel.Entry>, sectionType: RecipeSectionView.SectionType) {
        this.sectionType = sectionType
        this.data = data
        notifyDataSetChanged()
    }

    fun setCallbacks(callbacks: RecipesRecyclerView.Callbacks) {
        this.callbacks = callbacks
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(viewType, parent, false)
        return ViewHolder(view)
    }

    private val isHorizontalRow: Boolean
        get() = sectionType == RecipeSectionView.SectionType.HORIZONTAL_ROW

    override fun getItemViewType(position: Int): Int {
        return if (data?.get(position)?.thumbnailURL == null) {
            if (isHorizontalRow) {
                R.layout.item_recipe_no_photo_narrow
            } else {
                R.layout.item_recipe_no_photo
            }
        } else {
            if (isHorizontalRow) {
                R.layout.item_recipe_narrow
            } else {
                R.layout.item_recipe
            }
        }
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data?.getOrNull(position)?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageBackdrop: ImageView? = view.imageBackdrop
        private val titleLabel: TextView? = view.titleLabel

        init {
            titleLabel?.setOnClickListener {
                data?.getOrNull(adapterPosition)?.let { uiModel ->
                    callbacks?.onImageTapped(uiModel.recipeID)
                }
            }
            imageBackdrop?.setOnClickListener {
                data?.getOrNull(adapterPosition)?.let { uiModel ->
                    callbacks?.onImageTapped(uiModel.recipeID)
                }
            }
        }

        fun bind(data: RecipeSectionViewUiModel.Entry) {
            imageBackdrop?.let { imageView ->
                data.thumbnailURL?.let { backdropURL ->
                    ImageLoader.load(backdropURL, imageView)
                }
            }
            titleLabel?.text = data.title
        }
    }


}