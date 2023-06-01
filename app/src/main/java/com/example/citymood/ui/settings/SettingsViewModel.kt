package com.example.citymood.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class SettingsViewModel: ViewModel() {
	private val _text = MutableLiveData<String>().apply {
		value = "This is a settings fragment"
	}

	val fb = FirebaseAuth.getInstance().signOut()

	val text: LiveData<String> = _text
}