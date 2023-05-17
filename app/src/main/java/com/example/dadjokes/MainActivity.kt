package com.example.dadjokes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.dadjokes.fragments.AppbarFragment
import com.example.dadjokes.fragments.RecyclerFragment

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    private var currentFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            // Ripristina il fragment corrente
            currentFragment = supportFragmentManager.getFragment(savedInstanceState, "currentFragment")
        } else {
            // Crea e visualizza il fragment iniziale
            currentFragment = RecyclerFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, currentFragment!!)
                .commit()
        }

        val fragment = AppbarFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.container_appbar, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        // Salva lo stato del fragment corrente
        if (currentFragment != null) {
            supportFragmentManager.putFragment(outState, "currentFragment", currentFragment!!)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        currentFragment = supportFragmentManager.getFragment(savedInstanceState, "currentFragment")
        if (currentFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_main, currentFragment!!)
                .commit()
        }
    }


    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount

        if (backStackEntryCount > 0) {
            fragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }

        val mainPageFragment = RecyclerFragment() // Replace RecyclerFragment with your actual main page fragment class
        fragmentManager.beginTransaction()
            .replace(R.id.container_main, mainPageFragment)
            .commit()
    }
}
