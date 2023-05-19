package com.example.dadjokes.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.dadjokes.R

class AppbarFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_favourites -> {
                val favouritesFragment = FavouritesFragment() // Crea un'istanza del fragment delle preferite
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, favouritesFragment) // Sostituisci il fragment corrente con il fragment delle preferite
                    .addToBackStack(null) // Aggiungi la transazione alla back stack per consentire il ritorno al fragment precedente
                    .commit()
                true
            }
            R.id.action_search -> {
                val searchFragment = SearchFragment() // Crea un'istanza del fragment di ricerca
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, searchFragment) // Sostituisci il fragment corrente con il fragment di ricerca
                    .addToBackStack(null) // Aggiungi la transazione alla back stack per consentire il ritorno al fragment precedente
                    .commit()
                true
            }
            R.id.action_add -> {
                val addFragment = AddFragment() // Crea un'istanza del fragment di ricerca
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.container_main, addFragment) // Sostituisci il fragment corrente con il fragment di ricerca
                    .addToBackStack(null) // Aggiungi la transazione alla back stack per consentire il ritorno al fragment precedente
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}