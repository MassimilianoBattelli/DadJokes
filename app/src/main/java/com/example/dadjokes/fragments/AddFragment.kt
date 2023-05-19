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
import com.example.dadjokes.viewmodels.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddFragment : Fragment() {

    private lateinit var viewModel: AddViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(
            requireActivity(),
            AddViewModelFactory((requireActivity().application as MainApplication).repository)
        )[AddViewModel::class.java]

        val setupTextView: TextView = view.findViewById(R.id.setupTextView)
        val setupEditTExt: EditText = view.findViewById(R.id.setupEditText)

        val punchlineTextView: TextView = view.findViewById(R.id.punchlineTextView)
        val punchlineEditTExt: EditText = view.findViewById(R.id.punchlineEditText)

        val addButton: Button = view.findViewById(R.id.addButton)


        addButton.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val keyword = keywordEditText.text.toString()
                textViewResult.text = viewModel.searchJokeByKeyword(keyword)
            }
        }
    }
}
