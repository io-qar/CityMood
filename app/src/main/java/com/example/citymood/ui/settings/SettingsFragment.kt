package com.example.citymood.ui.settings

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.BuildCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.citymood.R
import com.example.citymood.SignInActivity


class SettingsFragment : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		setPreferencesFromResource(R.xml.root_preferences, rootKey)
		val preference = findPreference<Preference>(getString(R.string.pref_key_night))
		preference?.onPreferenceChangeListener = modeChangeListener
	}

	private val modeChangeListener =
		Preference.OnPreferenceChangeListener { _, newValue ->
			Log.i("newValue", newValue.toString())
			newValue as? String
			when (newValue) {
				getString(R.string.pref_night_on) -> {
					updateTheme(AppCompatDelegate.MODE_NIGHT_YES)
				}

				getString(R.string.pref_night_off) -> {
					updateTheme(AppCompatDelegate.MODE_NIGHT_NO)
				}

				else -> {
					if (BuildCompat.isAtLeastQ()) {
						AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
					} else {
						AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY)
					}
				}
			}
			true
		}

	private fun updateTheme(nightMode: Int): Boolean {
		AppCompatDelegate.setDefaultNightMode(nightMode)
		requireActivity().startActivity(Intent(context, SignInActivity::class.java))
		return true
	}
}