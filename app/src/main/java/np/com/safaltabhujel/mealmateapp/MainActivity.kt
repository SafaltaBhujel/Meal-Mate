package np.com.safaltabhujel.mealmateapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(np.com.safaltabhujel.mealmateapp.R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(np.com.safaltabhujel.mealmateapp.R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler().postDelayed(
            {
                startActivity(Intent(this, np.com.safaltabhujel.mealmateapp.LoginActivity::class.java))
            }, 3000
        )
    }
}