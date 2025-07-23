package np.com.safaltabhujel.mealmateapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import np.com.safaltabhujel.mealmateapp.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegistrationBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Handle the registration button click
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString().trim()
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            // Validate inputs
            if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Register the user using Firebase Authentication
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Optionally, you can save the name and other user details to Firebase Realtime Database or Firestore here
                        Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show()

                        // Navigate to HomeActivity after successful registration
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()  // Ensure the user can't go back to the RegistrationActivity
                    } else {
                        Toast.makeText(this, "Registration Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Option to navigate back to login activity
        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
