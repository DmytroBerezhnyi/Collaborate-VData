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
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpNavigation()
        setUpToolbar()
    }

    fun showToolbarPlusIcon() {
        ivAdd.visibility = View.VISIBLE
    }

    fun hideToolbarPlusIcon() {
        ivAdd.visibility = View.INVISIBLE
    }

    fun setOnIconClickedListener(listener: OnAddIconClickedListener) {
        ivAdd.setOnClickListener {
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
        NavigationUI.setupWithNavController(bottom_navigation, navController)
        return navController
    }

    private fun setUpToolbar() {
        val appBarConfiguration = AppBarConfiguration
            .Builder(R.id.companyFragment, R.id.collaboratorFragment)
            .build()
        setSupportActionBar(toolbar_main)
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration)
    }

    interface OnAddIconClickedListener {
        fun onAddButtonClicked()
    }
}