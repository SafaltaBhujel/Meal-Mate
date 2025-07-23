package np.com.safaltabhujel.mealmateapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import np.com.safaltabhujel.mealmateapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Force logout every time the app is opened
        auth.signOut()

        // Set text for login button
        binding.btnLogin.text = "Login"

        // Handle login button click
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email and Password cannot be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Sign in user with Firebase Authentication
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, DashboardActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Login Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Redirect to registration page
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, RegistrationActivity::class.java)
            startActivity(intent)
        }

        // Edge-to-edge support
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
