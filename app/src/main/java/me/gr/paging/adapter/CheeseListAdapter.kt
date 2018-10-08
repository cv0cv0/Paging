package me.gr.paging.adapter

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import me.gr.paging.R
import me.gr.paging.data.Cheese

class CheeseListAdapter : PagedListAdapter<Cheese, CheeseViewHolder>(CheeseDiffCallback()) {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CheeseViewHolder {
        return CheeseViewHolder(p0)
    }

    override fun onBindViewHolder(p0: CheeseViewHolder, p1: Int) {
        p0.bindTo(getItem(p1))
    }
}

class CheeseViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_cheese, parent, false)
) {
    private val nameText = itemView.findViewById<TextView>(R.id.name_text)
    var cheese: Cheese? = null

    fun bindTo(cheese: Cheese?) {
        this.cheese = cheese
        nameText.text = cheese?.name
    }
}

class CheeseDiffCallback : DiffUtil.ItemCallback<Cheese>() {
    override fun areItemsTheSame(p0: Cheese, p1: Cheese): Boolean {
        return p0.id == p1.id
    }

    override fun areContentsTheSame(p0: Cheese, p1: Cheese): Boolean {
        return p0.name == p1.name
    }
}