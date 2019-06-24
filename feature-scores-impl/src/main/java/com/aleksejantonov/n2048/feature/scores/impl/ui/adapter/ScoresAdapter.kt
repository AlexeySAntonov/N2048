package com.aleksejantonov.n2048.feature.scores.impl.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleksejantonov.n2048.feature.scores.impl.R
import com.aleksejantonov.n2048.feature.scores.impl.ui.ChartView
import com.aleksejantonov.n2048.model.Player
import kotlinx.android.synthetic.main.item_score.view.*

class ScoresAdapter : RecyclerView.Adapter<ScoresAdapter.ViewHolder>() {

    private var items: List<Player> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_score, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], position)
    }

    fun updateItems(items: List<Player>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Player, position: Int) {
            with(itemView) {
                name.text = item.name
                score.text = context.getString(R.string.top_player_score, item.score)
                colorIcon.setBackgroundColor(ChartView.colors[position % ChartView.colors.size])
            }
        }
    }
}