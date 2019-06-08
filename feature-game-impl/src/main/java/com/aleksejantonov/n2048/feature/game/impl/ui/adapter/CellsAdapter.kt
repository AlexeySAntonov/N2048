package com.aleksejantonov.n2048.feature.game.impl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleksejantonov.n2048.core.ui.base.DiffCalculator
import com.aleksejantonov.n2048.feature.game.impl.R
import kotlinx.android.synthetic.main.item_cell.view.*

class CellsAdapter : RecyclerView.Adapter<CellsAdapter.ViewHolder>() {

    private var items = listOf<Cell>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_cell, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateList(newList: List<Cell>) {
        val diffResult = DiffCalculator(items, newList).calculateDiff()
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Cell) {
            with(itemView) {
                numCell.text = item.value.toString()
            }
        }
    }
}