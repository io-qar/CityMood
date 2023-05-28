package com.example.citymood

import android.os.Bundle
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.citymood.databinding.ActivityShorkaBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityShorkaBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		binding = ActivityShorkaBinding.inflate(layoutInflater)

		setContentView(binding.root)

		setSupportActionBar(binding.appBarShorka.toolbar)

		binding.appBarShorka.fab.setOnClickListener { view ->
			Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
				.setAction("Action", null).show()
		}
		val drawerLayout: DrawerLayout = binding.drawerLayout
		val navView: NavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_content_shorka)

		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.nav_home, R.id.nav_settings, R.id.nav_about, R.id.nav_logout
			), drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.shorka, menu)

		val firebaseUser = FirebaseAuth.getInstance().currentUser?.email
		val tv = findViewById<TextView>(R.id.navHeaderTextView)
		tv.text = firebaseUser.toString()

		return true
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.nav_host_fragment_content_shorka)
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}