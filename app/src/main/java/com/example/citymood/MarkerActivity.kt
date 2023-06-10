package com.example.citymood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.citymood.databinding.ActivityMarkerBinding
import com.example.citymood.entity.ColorAdapter
import com.example.citymood.entity.ColorList
import com.example.citymood.entity.ColorObj
import com.google.firebase.firestore.FirebaseFirestore


class MarkerActivity : AppCompatActivity() {
	private lateinit var db: FirebaseFirestore
	private lateinit var binding: ActivityMarkerBinding
	lateinit var selectedColor: ColorObj

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityMarkerBinding.inflate(layoutInflater)
		setContentView(binding.root)
		loadColorSpinner()
		Log.i("loadspinner", selectedColor.hsv.toString())

		db = FirebaseFirestore.getInstance()

		val latIntent = intent.getDoubleExtra("latitude", 55.755833)
		val longIntent = intent.getDoubleExtra("longitude", 37.617778)

		val latitudeEdt = findViewById<EditText>(R.id.editLat)
		latitudeEdt.isLongClickable = false
		latitudeEdt.setText(latIntent.toString())
		val longitudeEdt = findViewById<EditText>(R.id.editLong)
		longitudeEdt.isLongClickable = false
		longitudeEdt.setText(longIntent.toString())

		val button1 = findViewById<Button>(R.id.saveButton)

		button1.setOnClickListener{
			view -> val intent = Intent(view.context, MainActivity::class.java)

			val lat = latitudeEdt.text.toString().toDouble()
			val long = longitudeEdt.text.toString().toDouble()

			val marker = HashMap<String, Any>()
			marker["Latitude"] = lat
			marker["Longitude"] = long
			marker["Color"] = selectedColor.hsv

			Log.i("color", selectedColor.hsv.toString())
			db.collection("markers").add(marker)
				.addOnSuccessListener {
					Toast.makeText(this, "db adding success", Toast.LENGTH_LONG).show()
				}
				.addOnFailureListener {
					Toast.makeText(this, "db adding fail", Toast.LENGTH_LONG).show()
				}
			Toast.makeText(this, "settings were saved\n{$lat}\n{$long}\n{${selectedColor.hsv}}", Toast.LENGTH_LONG).show()
			view.context.startActivity(intent)
		}
	}

		private fun loadColorSpinner() {
		selectedColor = ColorList().defaultValue
		binding.editColor.apply{
			adapter = ColorAdapter(applicationContext, ColorList().basics())
			setSelection(ColorList().colorPosition(selectedColor), false)
			onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
				override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
					selectedColor = ColorList().basics()[position]
				}
				override fun onNothingSelected(p0: AdapterView<*>?) {}
			}
		}
	}
}