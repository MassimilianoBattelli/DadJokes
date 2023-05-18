package com.example.dadjokes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.dadjokes.MainApplication
import com.example.dadjokes.R
import com.example.dadjokes.viewmodels.FavouritesViewModel
import com.example.dadjokes.viewmodels.FavouritesViewModelFactory
import com.example.dadjokes.viewmodels.SearchViewModel
import com.example.dadjokes.viewmodels.SearchViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            SearchViewModelFactory((requireActivity().application as MainApplication).repository)
        )[SearchViewModel::class.java]

        val keywordEditText: EditText = view.findViewById(R.id.keywordEditText)
        val searchButton: Button = view.findViewById(R.id.searchButton)
        val textViewResult: TextView = view.findViewById(R.id.textViewResult)

        searchButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val keyword = keywordEditText.text.toString()
                textViewResult.text = viewModel.searchJokeByKeyword(keyword)
            }
        }
        /*
        searchViewModel.jokeResult.observe(viewLifecycleOwner) { joke ->
            resultTextView.text = joke
        }
         */
    }
}
