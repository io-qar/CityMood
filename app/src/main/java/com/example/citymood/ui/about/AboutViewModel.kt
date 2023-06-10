package com.example.citymood.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {
	private val _text = MutableLiveData<String>().apply {
		value = "CityMood - это приложение для Android, которое помогает пользователям узнать настроение города, в котором они находятся. Оно использует данные из социальных сетей и других онлайн-источников, чтобы определить общее настроение города и показать его на карте."
	}
	val text: LiveData<String> = _text
}