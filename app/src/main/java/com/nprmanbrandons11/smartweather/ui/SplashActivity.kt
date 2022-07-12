package com.nprmanbrandons11.smartweather.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import com.nprmanbrandons11.smartweather.R
import com.nprmanbrandons11.smartweather.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lottieView = binding.animation
        lottieView.enableMergePathsForKitKatAndAbove(true)
        lottieView.setAnimation(R.raw.splash_lootie)
        lottieView.repeatCount = 1

        lottieView.playAnimation()

        lottieView.addAnimatorListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {

                startActivity(Intent(this@SplashActivity,MainActivity::class.java))

            }
        })
        //
    }
}