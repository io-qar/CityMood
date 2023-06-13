package com.example.citymood

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.citymood.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class SignInActivity : AppCompatActivity() {
	private lateinit var binding: ActivitySignInBinding
	private lateinit var firebaseAuth: FirebaseAuth

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivitySignInBinding.inflate(layoutInflater)
		setContentView(binding.root)
		firebaseAuth = FirebaseAuth.getInstance()
		binding.textView.setOnClickListener{
			val intent = Intent(this, SignUpActivity::class.java)
			startActivity(intent)
		}

		binding.button.setOnClickListener{
			val email = binding.emailEt.text.toString()
			val pass = binding.passET.text.toString()
			if (email.isNotEmpty() && pass.isNotEmpty()) {
				firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{
					if (it.isSuccessful) {
						val intent = Intent(this, MainActivity::class.java)
						startActivity(intent)
					} else {
						try {
							throw it.exception!!
						} catch (e: FirebaseAuthWeakPasswordException) {
							Toast.makeText(this, "Ваш пароль должен быть больше 6 символов!", Toast.LENGTH_SHORT).show()
						} catch (e: FirebaseAuthInvalidCredentialsException) {
							Toast.makeText(this, "Почта указана неверно, или введён неверный пароль", Toast.LENGTH_SHORT).show()
						} catch (e: FirebaseAuthUserCollisionException) {
							Toast.makeText(this, "Такой пользователь уже существует!", Toast.LENGTH_SHORT).show()
						} catch (e: Exception) {
							e.message?.let { it1 -> Log.e("Firebase", it1) }
							Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
						}
					}
				}
			} else {
				Toast.makeText(this, "Все поля должны быть заполнены!", Toast.LENGTH_SHORT).show()
			}
		}
	}

	override fun onStart() {
		super.onStart()
		if (firebaseAuth.currentUser != null) {
			val intent = Intent(this, MainActivity::class.java)
			startActivity(intent)
		}
	}
}