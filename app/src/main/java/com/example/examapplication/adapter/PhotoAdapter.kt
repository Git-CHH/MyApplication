package com.example.examapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.examapplication.R
import com.example.examapplication.model.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotoAdapter (private val onClick: (Photo) -> Unit) : ListAdapter<Photo, PhotoAdapter.ViewHolder>(
    DiffCallback()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    class ViewHolder(itemsView: View): RecyclerView.ViewHolder(itemsView) {
        fun bind(photo: Photo, onClick: (Photo) -> Unit) {
            with(itemView) {
                categoryTextView.text = photo.title
                Picasso.get().load(photo.thumbnailUrl).into(itemView.categoryImageView)
                categoryConstraintLayout.setOnClickListener { onClick(photo) }
            }
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem == newItem
        }
    }
}