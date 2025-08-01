package ir.devalix.konkura

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import ir.devalix.konkura.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.net.NetworkInterface

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (isVpnActive()) {
            Toast.makeText(
                this,
                "جهت بهبود در عملکرد برنامه لطفا vpn خود را خاموش کنید",
                Toast.LENGTH_LONG
            ).show()
        }



        val intent = Intent(this, MainActivity::class.java)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            onDestroy()
        }, 3000)
        val scaleAnim = ScaleAnimation(
            0f, 1f,
            0f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 1000
            fillAfter = true
        }
        val fadeAnim = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }
        val fadeAnimTxt = AlphaAnimation(0f, 1f).apply {
            duration = 1000
            fillAfter = true
        }
        val animSet = AnimationSet(true).apply {
            addAnimation(scaleAnim)
            addAnimation(fadeAnim)
        }
        binding.imgIconSplash.startAnimation(animSet)
        lifecycleScope.launch {
            delay(1000)
            binding.txtSplash.startAnimation(fadeAnimTxt)
        }


    }

    private fun isVpnActive(): Boolean {
        try {
            val interfaces = NetworkInterface.getNetworkInterfaces()
            for (networkInterface in interfaces) {
                if (!networkInterface.isUp || networkInterface.interfaceAddresses.isEmpty()) continue

                if (networkInterface.name.equals("tun0", ignoreCase = true) ||
                    networkInterface.name.equals("ppp0", ignoreCase = true)
                ) {
                    return true
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }


}