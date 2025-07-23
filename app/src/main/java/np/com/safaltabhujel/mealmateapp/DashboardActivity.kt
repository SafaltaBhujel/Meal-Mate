package np.com.safaltabhujel.mealmateapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import np.com.safaltabhujel.mealmateapp.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(np.com.safaltabhujel.mealmateapp.R.id.nav_host_fragment_activity_dashboard)

        navView.setupWithNavController(navController)
    }
}