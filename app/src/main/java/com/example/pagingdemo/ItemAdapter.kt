package com.example.pagingdemo

import android.content.Context
import androidx.paging.PagedListAdapter
import android.view.View
import android.widget.ImageView

import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast

import com.bumptech.glide.Glide

import com.example.pagingdemo.ItemAdapter.ItemViewHolder

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil



class ItemAdapter : PagedListAdapter<Item,ItemViewHolder>(DIFF_CALLBACK) {
    private lateinit var mCtx :Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.userslist, parent, false)
        mCtx = parent.context
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.textView.text = item.owner!!.display_name
            Glide.with(mCtx)
                .load(item.owner!!.profile_image)
                .into(holder.imageView)
        } else {
            Toast.makeText(mCtx, "Item is null", Toast.LENGTH_LONG).show()
        }
    }
    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Item> =
            object : DiffUtil.ItemCallback<Item>() {
                override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem.question_id == newItem.question_id
                }

                override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                    return oldItem.equals(newItem)
                }
            }
    }

     class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView
        var imageView: ImageView

        init {
            textView = itemView.findViewById(R.id.textViewName)
            imageView = itemView.findViewById(R.id.imageView)
        }
    }
}