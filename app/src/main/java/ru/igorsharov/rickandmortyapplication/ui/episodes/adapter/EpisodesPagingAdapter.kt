package ru.igorsharov.rickandmortyapplication.ui.episodes.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_episode.view.*
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.inflate
import ru.igorsharov.rickandmortyapplication.ui.entities.EpisodeView
import javax.inject.Inject

class EpisodesPagingAdapter @Inject constructor() :
    PagedListAdapter<EpisodeView, EpisodesPagingAdapter.ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<EpisodeView>() {
            override fun areItemsTheSame(oldItem: EpisodeView, newItem: EpisodeView): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: EpisodeView, newItem: EpisodeView): Boolean =
                newItem == oldItem
        }
    }

    var onClickListener: (Int) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_episode))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClickListener(adapterPosition) }
        }

        fun bind(itemData: EpisodeView) {
            with(itemView) {
                tvName.text = itemData.name
                tvAirDate.text = itemData.airDate
                tvEpisode.text = itemData.episode
            }
        }
    }
}