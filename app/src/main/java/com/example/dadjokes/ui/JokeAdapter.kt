package com.example.dadjokes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.R
import com.example.dadjokes.models.JokeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeAdapter(
    private val dataSource: List<JokeModel>,
    private val listener: JokeAdapterListener
) : RecyclerView.Adapter<JokeAdapter.ViewHolder>() {

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
        val imageHeart: ImageView

        init {
            // Define click listener for the ViewHolder's View
            txtSetup = view.findViewById(R.id.txtSetup)
            txtPunchline = view.findViewById(R.id.txtPunchline)
            imageHeart = view.findViewById(R.id.imageHeart)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_cardview, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val jokeModel = dataSource[position]
        viewHolder.txtSetup.text = jokeModel.setup
        viewHolder.txtPunchline.text = jokeModel.punchline


        CoroutineScope(Dispatchers.Main).launch {
            val isFavourite = listener.searchFavourite(jokeModel)

            val heartImageResource =
                if (isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
            viewHolder.imageHeart.setImageResource(heartImageResource)
        }

        viewHolder.imageHeart.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val isFavourite = listener.searchFavourite(jokeModel)
                listener.onFavouriteButtonClicked(jokeModel, isFavourite)

                val heartImageResource =
                    if (!isFavourite) R.drawable.baseline_favorite_24 else R.drawable.baseline_favorite_border_24
                viewHolder.imageHeart.setImageResource(heartImageResource)
            }
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSource.size
}