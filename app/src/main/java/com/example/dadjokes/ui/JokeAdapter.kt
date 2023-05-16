package com.example.dadjokes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.R
import com.example.dadjokes.models.JokeModel

class JokeAdapter(private val dataSource: List<JokeModel>) :
    RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     *     val setup: String,
            val punchline: String,
            val type: String
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtSetup: TextView
        val txtPunchline: TextView

        init {
            // Define click listener for the ViewHolder's View
            txtSetup = view.findViewById(R.id.txtSetup)
            txtPunchline = view.findViewById(R.id.txtPunchline)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_joke, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val jokeModel = dataSource[position]
        viewHolder.txtSetup.text = jokeModel.setup
        viewHolder.txtPunchline.text = jokeModel.punchline
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSource.size
}