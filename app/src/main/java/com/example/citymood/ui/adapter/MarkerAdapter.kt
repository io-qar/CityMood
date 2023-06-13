package com.example.citymood.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.citymood.R
import com.example.citymood.entity.Marker

class MarkerAdapter(var markerArrayList: ArrayList<Marker>) : RecyclerView.Adapter<MarkerAdapter.MarkerViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MarkerViewHolder {
		val inflater = LayoutInflater.from(parent.context)
		return MarkerViewHolder(inflater.inflate(R.layout.item_marker, parent, false))
	}

	override fun onBindViewHolder(holder: MarkerViewHolder, position: Int) {
		val marker: Marker = markerArrayList[position]

		holder.latView.text = marker.Latitude.toString()
		holder.longView.text = marker.Longitude.toString()
		val c = Color.HSVToColor(floatArrayOf(marker.Color, 1f, 1f))
		val cText = "#" + Integer.toHexString(c).substring(2)
		holder.colorView.setTextColor(c)
		holder.colorView.text = cText
	}

	override fun getItemCount(): Int {
		return markerArrayList.size
	}

	class MarkerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		 var latView: TextView = itemView.findViewById(R.id.marker_item_latitude)
		 var longView: TextView = itemView.findViewById(R.id.marker_item_longitude)
		 var colorView: TextView = itemView.findViewById(R.id.marker_item_color)
	}
}
