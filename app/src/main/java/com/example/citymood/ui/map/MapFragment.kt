package com.example.citymood.ui.map

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.citymood.MarkerActivity
import com.example.citymood.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore


class MapFragment : Fragment(), OnMapReadyCallback {
	private lateinit var db: FirebaseFirestore

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		super.onCreate(savedInstanceState)

		val view: View = inflater.inflate(R.layout.fragment_map, container, false)
		val supportMapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

		db = FirebaseFirestore.getInstance()
		supportMapFragment?.getMapAsync(this)

		return view
	}

	@SuppressLint("PotentialBehaviorOverride")
	override fun onMapReady(map: GoogleMap) {
		val MOSCOW = LatLng(55.755833, 37.617778)
		val radiusDegrees = 0.1
		val northEast = LatLng(MOSCOW.latitude + radiusDegrees, MOSCOW.longitude + radiusDegrees)
		val southWest = LatLng(MOSCOW.latitude - radiusDegrees, MOSCOW.longitude - radiusDegrees)
		val bounds = LatLngBounds.builder().include(northEast).include(southWest).build()

		map.setLatLngBoundsForCameraTarget(bounds)
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(MOSCOW, 10f))

		map.setOnMapClickListener{
			latlang -> map.clear()
			map.addMarker(MarkerOptions().position(latlang))
			Toast.makeText(context, "{$latlang}", Toast.LENGTH_LONG).show()

			val intent = Intent(context, MarkerActivity::class.java)
			intent.putExtra("latitude", latlang.latitude)
			intent.putExtra("longitude", latlang.longitude)
			startActivity(intent)
		}
	}
}