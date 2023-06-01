package com.example.citymood.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.citymood.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

class MapFragment : Fragment() {
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val view: View = inflater.inflate(R.layout.fragment_map, container, false)
		val supportMapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

		supportMapFragment!!.getMapAsync{ googleMap ->
			googleMap.setOnMapClickListener { latLng -> // When clicked on map
				val markerOptions = MarkerOptions()
				markerOptions.position(latLng)
				markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
				googleMap.clear()
				googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10f))
				googleMap.addMarker(markerOptions)
			}
		}

		return view
	}
}