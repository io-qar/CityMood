package com.example.citymood

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.citymood.databinding.ActivityShtorkaBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
	private lateinit var appBarConfiguration: AppBarConfiguration
	private lateinit var binding: ActivityShtorkaBinding
	lateinit var auth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		auth = FirebaseAuth.getInstance()
		binding = ActivityShtorkaBinding.inflate(layoutInflater)

		setContentView(binding.root)
		setSupportActionBar(binding.barShtorka.toolbar)

		val drawerLayout: DrawerLayout = binding.drawerLayout
		val navView: NavigationView = binding.navView
		val navController = findNavController(R.id.nav_host_fragment_content_shtorka)

		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.nav_home, R.id.nav_map, R.id.nav_about, R.id.nav_logout
			), drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)
	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		menuInflater.inflate(R.menu.shtorka, menu)

		val firebaseUser = auth.currentUser?.email
		val tv = findViewById<TextView>(R.id.navHeaderTextView)
		tv.text = firebaseUser.toString()

		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		if (item.itemId == R.id.action_settings) {
			auth.signOut()
			finish()
		}

		return super.onOptionsItemSelected(item)
	}
}