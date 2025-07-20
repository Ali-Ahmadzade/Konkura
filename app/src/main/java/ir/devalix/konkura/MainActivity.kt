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

class MainActivity : AppCompatActivity() {

    private var currentFragment = 2
    lateinit var binding: ActivityMainBinding
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

        binding.bottNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.botNavEnsani -> {
                    if (currentFragment == 1) {
                    } else {
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frameFragmentMain, EnsaniFragment())
                        fragmentTransaction.commit()
                        currentFragment = 1
                    }
                }

                R.id.botNavRiazi -> {
                    if (currentFragment == 3) {
                    } else {
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frameFragmentMain, RiaziFragment())
                        fragmentTransaction.commit()
                        currentFragment = 3
                    }
                }

                R.id.botNavTajrobi -> {
                    if (currentFragment == 2) {
                    } else {
                        val fragmentTransaction = supportFragmentManager.beginTransaction()
                        fragmentTransaction.replace(R.id.frameFragmentMain, TajrobiFragment())
                        fragmentTransaction.commit()
                        currentFragment = 2
                    }
                }
            }
            true
        }
    }

    private fun setupSplashScreen() {
        binding.bottNav.selectedItemId = R.id.botNavTajrobi

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