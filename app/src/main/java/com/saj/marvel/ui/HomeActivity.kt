package com.saj.marvel.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.saj.marvel.R
import com.saj.marvel.databinding.ActivityHomeBinding
import com.saj.marvel.viewModels.AuthStateViewModel
import com.saj.marvel.viewModels.singleEvent.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val authStateViewModel: AuthStateViewModel by viewModels()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        authStateViewModel.isLoggedInLiveData.observe(this, EventObserver {
            setStartDestination(it)
        })
    }

    private fun setStartDestination(isLoggedIn: Boolean) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment)
                as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val navGraph = inflater.inflate(R.navigation.nav_graph)

        val destination = if (isLoggedIn) R.id.charactersListFragment else R.id.signInFragment
        navGraph.startDestination = destination
        navHostFragment.navController.graph = navGraph
    }
}