package com.example.mad03_fragments_and_navigation

import android.app.Activity
import android.app.TaskStackBuilder
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.mad03_fragments_and_navigation.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var binding: ActivityMainBinding

    override fun onStart() {
        super.onStart()
        Log.i("MainActivity", "I am in 'onStart()'")
    }

    override fun onPause() {
        super.onPause()
        Log.i("MainActivity", "I am in 'onPause()'")
    }

    override fun onResume() {
        super.onResume()
        Log.i("MainActivity", "I am in 'onResume()'")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity", "I am in 'onDestroy()'")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("MainActivity", "I am in 'onRestart()'")
    }

    override fun onStop() {
        super.onStop()
        Log.i("MainActivity", "I am in 'onStop()'")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity", "I am in 'onCreate()'")

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        drawerLayout = binding.drawerLayout

        // get tha navController of our NavHostFragment
        navController = this.findNavController(R.id.navhostFragment)
        // setup the action bar/top menu with our navController
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

        NavigationUI.setupWithNavController(binding.navView, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerLayout)
    }
}
