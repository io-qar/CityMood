package com.example.citymood.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.citymood.R
import com.example.citymood.entity.ColorObj

class ColorAdapter(ctx: Context, list: List<ColorObj>): ArrayAdapter<ColorObj>(ctx, 0, list) {
	private val layoutInflater = LayoutInflater.from(ctx)

	@SuppressLint("ViewHolder", "InflateParams")
	override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
		val view: View = layoutInflater.inflate(R.layout.color_spinner_bg, null, true)
		return view(view, position)
	}

	override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
		var cv = convertView
		if (cv == null) cv = layoutInflater.inflate(R.layout.color_spinner_item, parent, false)
		return view(cv!!, position)
	}

	private fun view(view: View, position: Int): View {
		val color: ColorObj = (getItem(position)?: return view)

		val colorNameItem = view.findViewById<TextView>(R.id.colorName)
		val colorNameBG = view.findViewById<TextView>(R.id.colorNameBG)
		val colorBlob = view.findViewById<View>(R.id.colorBlob)

		colorNameBG?.text = color.name
		colorNameBG?.setTextColor(Color.parseColor(color.hexHash))
		colorNameItem?.text = color.name
		colorBlob?.background?.setTint(Color.parseColor(color.hexHash))

		return view
	}
}