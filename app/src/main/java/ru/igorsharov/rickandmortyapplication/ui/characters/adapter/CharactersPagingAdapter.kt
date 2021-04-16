package ru.igorsharov.rickandmortyapplication.ui.characters.adapter

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_character.view.*
import kotlinx.android.synthetic.main.item_episode.view.tvName
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.inflate
import ru.igorsharov.rickandmortyapplication.core.extension.loadToCircleFromUrl
import ru.igorsharov.rickandmortyapplication.ui.entities.CharacterView
import javax.inject.Inject

class CharactersPagingAdapter @Inject constructor() :
    PagedListAdapter<CharacterView, CharactersPagingAdapter.ViewHolder>(comparator) {

    companion object {
        private val comparator = object : DiffUtil.ItemCallback<CharacterView>() {
            override fun areItemsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean =
                oldItem.name == newItem.name

            override fun areContentsTheSame(oldItem: CharacterView, newItem: CharacterView) =
                newItem == oldItem
        }
    }

    var onClickListener: (Int) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_character))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClickListener(adapterPosition) }
        }

        fun bind(itemData: CharacterView) {
            with(itemView) {
                tvName.text = itemData.name
                tvGender.text = itemData.gender
                tvSpecies.text = itemData.species
                tvStatus.text = itemData.status
                ivImage.loadToCircleFromUrl(itemData.image)
            }
        }
    }
}