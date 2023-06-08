package com.example.citymood

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.firebase.firestore.FirebaseFirestore


class MarkerActivity : FragmentActivity() {
	private lateinit var db: FirebaseFirestore

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_marker)

		db = FirebaseFirestore.getInstance()

		val latIntent = intent.getDoubleExtra("latitude", 55.755833)
		val longIntent = intent.getDoubleExtra("longitude", 37.617778)

		val latitudeEdt = findViewById<EditText>(R.id.editLat)
		latitudeEdt.isLongClickable = false
		latitudeEdt.setText(latIntent.toString())
		val longitudeEdt = findViewById<EditText>(R.id.editLong)
		longitudeEdt.isLongClickable = false
		longitudeEdt.setText(longIntent.toString())

		val colEdt = findViewById<EditText>(R.id.editColor)
		val button1 = findViewById<Button>(R.id.saveButton)

		button1.setOnClickListener{
			view -> val intent = Intent(view.context, MainActivity::class.java)

			val lat = latitudeEdt.text.toString()
			val long = longitudeEdt.text.toString()
			val col = colEdt.text.toString()

			val marker = HashMap<String, Any>()
			marker["Latitude"] = lat
			marker["Longitude"] = long
			marker["Color"] = col

			if (col.isEmpty()) {
				Toast.makeText(applicationContext, "Please Enter the Data", Toast.LENGTH_SHORT).show()
			}
			db.collection("markers").add(marker)
				.addOnSuccessListener {
					Toast.makeText(this, "db adding success", Toast.LENGTH_LONG).show()
				}
				.addOnFailureListener {
					Toast.makeText(this, "db adding fail", Toast.LENGTH_LONG).show()
				}
			Toast.makeText(this, "settings were saved\n{$lat}\n{$long}\n{$col}", Toast.LENGTH_LONG).show()
			view.context.startActivity(intent)
		}
	}
}