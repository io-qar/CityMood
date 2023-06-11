package com.example.citymood.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citymood.R
import com.example.citymood.entity.Marker
import com.example.citymood.ui.adapter.MarkerAdapter
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot


class HomeFragment : Fragment() {
	private lateinit var markerAdapter: MarkerAdapter
	private lateinit var progressBar: ProgressBar
	private lateinit var markerArrayList: ArrayList<Marker>
	private lateinit var db: FirebaseFirestore
	lateinit var recyclerView: RecyclerView

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		super.onCreate(savedInstanceState)
		val view: View = inflater.inflate(R.layout.fragment_home, container, false)

		progressBar = view.findViewById(R.id.progressbar)
		progressBar.visibility = View.VISIBLE

		recyclerView = view.findViewById(R.id.markerss_recycler)
		recyclerView.setHasFixedSize(true)
		recyclerView.layoutManager = LinearLayoutManager(view.context)

		db = FirebaseFirestore.getInstance()
		markerArrayList = ArrayList()
		markerAdapter = MarkerAdapter(markerArrayList)

		recyclerView.adapter = markerAdapter
		EventListener()

		return view
	}

	@SuppressLint("NotifyDataSetChanged")
	private fun EventListener() {
		db.collection("markers").addSnapshotListener {
			querySnapshot: QuerySnapshot?, firebaseFirestoreException: FirebaseFirestoreException? ->
				if (firebaseFirestoreException != null) {
					if (progressBar.isAnimating) progressBar.visibility = View.INVISIBLE
					firebaseFirestoreException.message?.let{
						Log.e("firebase", it)
						return@let
					}
				}
				for (dc in querySnapshot?.documentChanges!!) {
					if (dc.type == DocumentChange.Type.ADDED) {
						markerArrayList.add(dc.document.toObject(Marker::class.java))
					}
					markerAdapter.notifyDataSetChanged()
					if (progressBar.isAnimating) progressBar.visibility = View.INVISIBLE
				}
		}
	}
}