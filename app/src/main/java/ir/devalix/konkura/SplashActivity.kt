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
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import ir.devalix.konkura.databinding.ActivitySplashBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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

        val intent = Intent(this , PdfActivity::class.java)
        startActivity(intent)

//        val intent = Intent(this, MainActivity::class.java)
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(intent)
//            onDestroy()
//        }, 1)

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



        val animSet = AnimationSet( true ).apply {
            addAnimation( scaleAnim)
            addAnimation( fadeAnim )
        }

        binding.imgIconSplash.startAnimation(animSet)

        lifecycleScope.launch {
            delay(1000)
            binding.txtSplash.startAnimation( fadeAnimTxt )
        }




    }


}