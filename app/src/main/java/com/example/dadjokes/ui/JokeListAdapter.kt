package com.example.dadjokes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.R
import com.example.dadjokes.local.entities.JokeEntity

class JokeListAdapter : ListAdapter<JokeEntity, JokeListAdapter.JokeViewHolder>(JokesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JokeViewHolder {
        return JokeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: JokeViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.joke)
    }

    class JokeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val JokeItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            JokeItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): JokeViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return JokeViewHolder(view)
            }
        }
    }

    class JokesComparator : DiffUtil.ItemCallback<JokeEntity>() {
        override fun areItemsTheSame(oldItem: JokeEntity, newItem: JokeEntity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: JokeEntity, newItem: JokeEntity): Boolean {
            return oldItem.joke == newItem.joke
        }
    }
}