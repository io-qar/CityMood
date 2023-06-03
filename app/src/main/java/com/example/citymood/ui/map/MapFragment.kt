package com.example.citymood.ui.map
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RawRes
import androidx.fragment.app.Fragment
import com.example.citymood.R
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.TileOverlayOptions
import com.google.maps.android.heatmaps.HeatmapTileProvider
import org.json.JSONArray
import org.json.JSONException
import java.util.Scanner


class MapFragment : Fragment(), OnMapReadyCallback {
	lateinit var map: GoogleMap

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		val view: View = inflater.inflate(R.layout.fragment_map, container, false)
		val supportMapFragment = childFragmentManager.findFragmentById(R.id.google_map) as SupportMapFragment?

		supportMapFragment?.getMapAsync(this)

		return view
	}

	private fun addHeatMap() {
		var latLngs: List<LatLng?>? = null

		// Get the data: latitude/longitude positions of police stations.
		try {
//			latLngs = readItems(R.raw.police_stations)
		} catch (e: JSONException) {
			Toast.makeText(context, "Problem reading list of locations.", Toast.LENGTH_LONG).show()
		}

		// Create a heat map tile provider, passing it the latlngs of the police stations.
		val provider = HeatmapTileProvider.Builder().data(latLngs).build()

		// Add a tile overlay to the map, using the heat map tile provider.
		val overlay = map.addTileOverlay(TileOverlayOptions().tileProvider(provider))
	}

	@Throws(JSONException::class)
	private fun readItems(@RawRes resource: Int): List<LatLng?> {
		val result: MutableList<LatLng?> = ArrayList()
		val inputStream = context?.resources?.openRawResource(resource)
		val json = Scanner(inputStream).useDelimiter("\\A").next()
		val array = JSONArray(json)
		for (i in 0 until array.length()) {
			val `object` = array.getJSONObject(i)
			val lat = `object`.getDouble("lat")
			val lng = `object`.getDouble("lng")
			result.add(LatLng(lat, lng))
		}
		return result
	}

	override fun onMapReady(map: GoogleMap) {
		val MOSCOW = LatLng(55.755833, 37.617778)
		val radiusDegrees = 0.1
		val northEast = LatLng(MOSCOW.latitude + radiusDegrees, MOSCOW.longitude + radiusDegrees)
		val southWest = LatLng(MOSCOW.latitude - radiusDegrees, MOSCOW.longitude - radiusDegrees)
		val bounds = LatLngBounds.builder().include(northEast).include(southWest).build()

		map.setLatLngBoundsForCameraTarget(bounds)
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(MOSCOW, 10f))
	}
}