package com.aleksejantonov.n2048.feature.chooseplayer.impl.ui.choose.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aleksejantonov.n2048.core.ui.base.isVisible
import com.aleksejantonov.n2048.feature.chooseplayer.impl.R
import com.aleksejantonov.n2048.model.Player
import kotlinx.android.synthetic.main.item_player.view.*

class PlayersAdapter(
    private val deleteListener: (Long) -> Unit,
    private val selectListener: (Long) -> Unit
) : RecyclerView.Adapter<PlayersAdapter.ViewHolder>() {

    private var items: List<Player> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_player, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<Player>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Player) {
            with(itemView) {
                name.text = item.name
                score.text = context.getString(R.string.player_score, item.score)
                checkIcon.isVisible = item.isSelected
                anonImg.isVisible = item.isAnon
                deleteIcon.setOnClickListener { deleteListener.invoke(item.id) }
                setOnClickListener { selectListener.invoke(item.id) }
            }
        }
    }
}