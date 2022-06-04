package com.example.dmytroberezhnyi_vdatatesttask.presentation.ui.activity

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.dmytroberezhnyi_vdatatesttask.R
import com.example.dmytroberezhnyi_vdatatesttask.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        setUpToolbar()
    }

    fun showToolbarPlusIcon() {
        binding.ivAdd.visibility = View.VISIBLE
    }

    fun hideToolbarPlusIcon() {
        binding.ivAdd.visibility = View.INVISIBLE
    }

    fun setOnIconClickedListener(listener: OnAddIconClickedListener) {
        binding.ivAdd.setOnClickListener {
            listener.onAddButtonClicked()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        super.onOptionsItemSelected(item)
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return true
    }

    private fun setUpNavigation(): NavController {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        NavigationUI.setupWithNavController(binding.bottomNavigation, navController)
        return navController
    }

    private fun setUpToolbar() {
        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.companyFragment, R.id.collaboratorFragment)
            .build()
        setSupportActionBar(binding.toolbarMain)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    interface OnAddIconClickedListener {
        fun onAddButtonClicked()
    }
}