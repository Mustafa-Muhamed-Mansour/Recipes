package com.recipes.app.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.recipes.R
import com.recipes.databinding.ItemRecipeBinding
import com.recipes.domain.entity.RecipeModel

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {


    private val differCallBack = object : DiffUtil.ItemCallback<RecipeModel?>() {
        override fun areItemsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem?.name == newItem?.name && oldItem?.cuisine == newItem?.cuisine && oldItem?.image == newItem?.image
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: RecipeModel, newItem: RecipeModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallBack)


    class RecipeViewHolder(binding: ItemRecipeBinding) : ViewHolder(binding.root) {
        val bind = binding
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            ItemRecipeBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        differ.currentList[position]?.let { recipe ->
            holder.bind.itemImgThumbnail.load(data = recipe?.image) {
                crossfade(true)
                crossfade(3000)
                placeholder(R.drawable.recipe)
                error(R.drawable.recipe)

            }
            holder.bind.itemTxtName.text = recipe?.name
            holder.bind.cuisine.text = recipe?.cuisine
        }
    }
}