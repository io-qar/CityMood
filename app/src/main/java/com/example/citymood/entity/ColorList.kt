package com.example.citymood.entity

import com.google.android.gms.maps.model.BitmapDescriptorFactory

class ColorList {
	val defaultValue: ColorObj = basics()[0]

	fun colorPosition(c: ColorObj): Int {
		for (i in basics().indices) {
			if (c == basics()[i]) return i
		}
		return 0
	}

	fun basics(): List<ColorObj> {
		return listOf(
			ColorObj("Лазурный", BitmapDescriptorFactory.HUE_AZURE, "f0ffff"),
			ColorObj("Голубой", BitmapDescriptorFactory.HUE_BLUE, "0000ff"),
			ColorObj("Циановый", BitmapDescriptorFactory.HUE_CYAN, "00ffff"),
			ColorObj("Зелёный", BitmapDescriptorFactory.HUE_GREEN, "008000"),
			ColorObj("Пурпурный", BitmapDescriptorFactory.HUE_MAGENTA, "ff00ff"),
			ColorObj("Оранжевый", BitmapDescriptorFactory.HUE_ORANGE, "ffa500"),
			ColorObj("Красный", BitmapDescriptorFactory.HUE_RED, "ff0000"),
			ColorObj("Розовый", BitmapDescriptorFactory.HUE_ROSE, "fbcce7"),
			ColorObj("Фиолетовый", BitmapDescriptorFactory.HUE_VIOLET, "ee82ee"),
			ColorObj("Жёлтый", BitmapDescriptorFactory.HUE_YELLOW, "ffff00"),
		)
	}
}