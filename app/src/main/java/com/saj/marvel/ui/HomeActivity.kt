package com.saj.marvel.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.saj.marvel.R
import com.saj.marvel.databinding.ActivityHomeBinding
import com.saj.marvel.viewModels.AuthStateViewModel
import com.saj.marvel.viewModels.singleEvent.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    companion object {
        private val BOTTOM_NAV_FRAGS : List<Int> = listOf(
            R.id.charactersListFragment,
            R.id.eventsListFragment
        )
        private val TOP_BAR_FRAGS : List<Int> = BOTTOM_NAV_FRAGS
    }

    private val authStateViewModel: AuthStateViewModel by viewModels()

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setUpBottomNavigation()

        authStateViewModel.isLoggedInLiveData.observe(this, EventObserver {
            setStartDestination(it)
        })
    }

    private fun setUpBottomNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)

        binding.bottomNav.itemIconTintList = null

        navController.addOnDestinationChangedListener { _, destination, _ ->
            val destinationId = destination.id
            binding.bottomNav.visibility = if (destinationId in BOTTOM_NAV_FRAGS) View.VISIBLE else View.GONE
            binding.topBar.visibility = if (destinationId in TOP_BAR_FRAGS) View.VISIBLE else View.GONE
        }
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