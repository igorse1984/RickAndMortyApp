package ru.igorsharov.rickandmortyapplication.ui.locations.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_episode.view.tvName
import kotlinx.android.synthetic.main.item_location.view.*
import ru.igorsharov.rickandmortyapplication.R
import ru.igorsharov.rickandmortyapplication.core.extension.inflate
import ru.igorsharov.rickandmortyapplication.ui.entities.LocationView
import javax.inject.Inject
import kotlin.properties.Delegates

class LocationsAdapter @Inject constructor(
) : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
    var collection: List<LocationView> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    var onClickListener: (Int) -> Unit = { _ -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(parent.inflate(R.layout.item_location))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(collection[position])
    }

    override fun getItemCount() = collection.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener { onClickListener(adapterPosition) }
        }

        fun bind(itemData: LocationView) {
            with(itemView) {
                tvName.text = itemData.name
                tvType.text = itemData.type
                tvDimension.text = itemData.dimension
            }
        }
    }
}