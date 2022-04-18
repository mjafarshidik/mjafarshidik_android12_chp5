package com.penatabahasa.suitgamev3.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.penatabahasa.suitgamev3.databinding.ActivitySplashBinding
import com.penatabahasa.suitgamev3.ui.onboarding.OnBoardingActivity
import kotlinx.coroutines.*

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var splashScreenBinding: ActivitySplashBinding
    private val time = 5000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashScreenBinding.root)

        splashScreenBinding.apply {
            Glide.with(this@SplashActivity)
                .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/splash_screen1.png")
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivSplashOne)

            Glide.with(this@SplashActivity)
                .load("https://raw.githubusercontent.com/mjafarshidik/assets-chapter5/master/PNG/splash_screen2.png")
                .override(500, 500)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivSplashTwo)
        }
        withCoroutine(time)
    }

    private fun withCoroutine(time: Long) {
        val mScope = CoroutineScope(Dispatchers.IO)
        mScope.launch {
            delay(time)
            withContext(Dispatchers.Main) {
                launchPostSplashActivity()
                mScope.cancel(null)
            }
        }
    }

    private fun launchPostSplashActivity() {
        val intent = Intent(this, OnBoardingActivity::class.java)
        startActivity(intent)
        finish()
    }
}