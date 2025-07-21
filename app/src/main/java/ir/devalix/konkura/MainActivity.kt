package ir.devalix.konkura

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import ir.devalix.konkura.databinding.ActivityMainBinding
import ir.devalix.konkura.fragments.EnsaniFragment
import ir.devalix.konkura.fragments.RiaziFragment
import ir.devalix.konkura.fragments.TajrobiFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import np.com.susanthapa.curved_bottom_navigation.CbnMenuItem

class MainActivity : AppCompatActivity() {

    private var currentFragment = 1
    private lateinit var binding: ActivityMainBinding
    private var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {

        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupSplashScreen()
        lifecycleScope.launch {
            delay(1)
            keepSplashScreen = false
        }

        binding.curveBotNav.setOnMenuItemClickListener { cbnMenuItem, i ->
            when (i) {
                0 -> {
                    if (currentFragment != i) {
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frameFragmentMain, EnsaniFragment())
                        fragmentManager.commit()
                        currentFragment = i
                    }
                }

                1 -> {
                    if (currentFragment != i) {
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frameFragmentMain, TajrobiFragment())
                        fragmentManager.commit()
                        currentFragment = i
                    }
                }

                2 -> {
                    if (currentFragment != i) {
                        val fragmentManager = supportFragmentManager.beginTransaction()
                        fragmentManager.replace(R.id.frameFragmentMain, RiaziFragment())
                        fragmentManager.commit()
                        currentFragment = i
                    }
                }
            }
        }


    }

    private fun setupSplashScreen() {

        val menuItem = arrayOf(
            CbnMenuItem(
                R.drawable.ic_lawyer, R.drawable.lawyer_animated, 0, "انسانی"
            ),
            CbnMenuItem(
                R.drawable.ic_doctor, R.drawable.doctor_animated, 0, "تجربی"
            ),
            CbnMenuItem(
                R.drawable.ic_engineer, R.drawable.engineer_animated, 0, "ریاضی"
            ),
        )
        binding.curveBotNav.setMenuItems(menuItem, currentFragment)

        val fragmentManager = supportFragmentManager.beginTransaction()
        fragmentManager.add(R.id.frameFragmentMain, TajrobiFragment())
        fragmentManager.commit()

        val setupFragment = supportFragmentManager.beginTransaction()
        setupFragment.add(R.id.frameFragmentMain, TajrobiFragment())
        setupFragment.commit()

        val splashScreen = installSplashScreen()

        splashScreen.setKeepOnScreenCondition {
            keepSplashScreen
        }

        splashScreen.setOnExitAnimationListener { splashProvider ->
            splashProvider.view.animate()
                .alpha(0f)
                .setDuration(300)
                .withEndAction {
                    splashProvider.remove()
                }.start()
        }
    }


}