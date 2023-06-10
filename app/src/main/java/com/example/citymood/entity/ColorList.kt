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
			ColorObj("Azure", BitmapDescriptorFactory.HUE_AZURE, "f0ffff"),
			ColorObj("Blue", BitmapDescriptorFactory.HUE_BLUE, "0000ff"),
			ColorObj("Cyan", BitmapDescriptorFactory.HUE_CYAN, "00ffff"),
			ColorObj("Green", BitmapDescriptorFactory.HUE_GREEN, "008000"),
			ColorObj("Magenta", BitmapDescriptorFactory.HUE_MAGENTA, "ff00ff"),
			ColorObj("Orange", BitmapDescriptorFactory.HUE_ORANGE, "ffa500"),
			ColorObj("Red", BitmapDescriptorFactory.HUE_RED, "ff0000"),
			ColorObj("Rose", BitmapDescriptorFactory.HUE_ROSE, "fbcce7"),
			ColorObj("Violet", BitmapDescriptorFactory.HUE_VIOLET, "ee82ee"),
			ColorObj("Yellow", BitmapDescriptorFactory.HUE_YELLOW, "ffff00"),
		)
	}
}