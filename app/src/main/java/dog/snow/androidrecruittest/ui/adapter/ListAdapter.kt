package dog.snow.androidrecruittest.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import dog.snow.androidrecruittest.R
import dog.snow.androidrecruittest.ui.model.ListItem
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<ListItem> = ArrayList()
    private var filteredList: List<ListItem> = ArrayList()
    var onItemClick: ((ListItem) -> Unit)? = null

    init {
        filteredList = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun filterList(filteredList: ArrayList<ListItem>) {
        items = filteredList
        notifyDataSetChanged()
    }

    fun submitList(listItem: List<ListItem>) {
        items = listItem
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ItemViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    inner class ItemViewHolder
    constructor(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val ivThumb = itemView.iv_thumb
        private val tvTitle = itemView.tv_photo_title
        private val tvAlbumTitle = itemView.tv_album_title

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(items[adapterPosition])
            }
        }

        fun bind(listItem: ListItem) {

            Picasso.get()
                .load(listItem.thumbnailUrl)
                .into(ivThumb)

            tvTitle.text = listItem.title
            tvAlbumTitle.text = listItem.albumTitle
        }
    }
}
