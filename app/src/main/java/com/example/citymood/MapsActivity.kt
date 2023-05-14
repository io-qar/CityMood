package com.example.citymood

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


internal class MapsActivity : AppCompatActivity(), OnMapReadyCallback {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_maps)

		val mapFragment = supportFragmentManager.findFragmentById(R.id.fragments) as? SupportMapFragment
		mapFragment?.getMapAsync(this)
	}

	/**
	 * Manipulates the map once available.
	 * This callback is triggered when the map is ready to be used.
	 * This is where we can add markers or lines, add listeners or move the camera. In this case,
	 * we just add a marker near Sydney, Australia.
	 * If Google Play services is not installed on the device, the user will be prompted to install
	 * it inside the SupportMapFragment. This method will only be triggered once the user has
	 * installed Google Play services and returned to the app.
	 */
	override fun onMapReady(googleMap: GoogleMap) {
		googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL)
		val loc = LatLng(-33.852, 151.211)
		// Add a marker in Sydney and move the camera
		googleMap.addMarker(
			MarkerOptions()
				.position(loc)
				.title("Marker")
				.draggable(true)
		)
		googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc))
		googleMap.moveCamera(CameraUpdateFactory.zoomTo(10F))
	}
}