package com.example.dadjokes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dadjokes.MainApplication
import com.example.dadjokes.R
import com.example.dadjokes.models.JokeModel
import com.example.dadjokes.ui.JokeAdapter
import com.example.dadjokes.viewmodels.HomePageViewModelFactory
import com.example.dadjokes.viewmodels.HomeViewModel

class RecyclerFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_recycler, container, false)
        recyclerView = view.findViewById(R.id.recyclerview)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity(), HomePageViewModelFactory((requireActivity().application as MainApplication).repository))[HomeViewModel::class.java]

        viewModel.jokes.observe(viewLifecycleOwner) { jokes ->
            bindJokes(jokes)
        }

        viewModel.getJokeList()
    }

    private fun bindJokes(jokes: List<JokeModel>) {
        val adapter = JokeAdapter(jokes)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}


