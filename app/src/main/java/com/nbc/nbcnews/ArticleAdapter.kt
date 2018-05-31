package com.nbc.nbcnews

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nbc.nbcnews.MainActivity.Item
import kotlinx.android.synthetic.main.articles_list_item.view.*

class ArticleAdapter(var items : ArrayList<Item>, val context: Context, val itemClickListener: (View, Int, Int) -> Unit) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder: ViewHolder = ViewHolder(LayoutInflater.from(context).inflate(R.layout.articles_list_item, parent, false))
        viewHolder.onClick(itemClickListener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var curItem: Item = items.get(position)
        holder.tvAnimalType?.text = curItem.headline
    }

    fun updateArticles(newItems: ArrayList<Item>) {
        items = newItems
    }

    fun <T : RecyclerView.ViewHolder> T.onClick(event: (view: View, position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(it, getAdapterPosition(), getItemViewType())
        }
        return this
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    var tvAnimalType = view.tv_animal_type
}