package com.example.co_leaner.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.co_leaner.R
import com.example.co_leaner.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.name
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as? NavHostFragment
        val navController = navHostFragment?.navController
        Log.d(tag, navController.toString())

        val bottomNav = binding.bottomNavigationView
        if (navController != null) {
            bottomNav.setupWithNavController(navController)
        }

        bottomNav.setOnItemReselectedListener {}

        navController?.addOnDestinationChangedListener { _,
                                                         destination,
                                                         _ ->
            if (destination.id == R.id.courseDetailFragment)
                bottomNav.visibility = GONE
            else
                bottomNav.visibility = VISIBLE
        }
    }
}