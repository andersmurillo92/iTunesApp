package com.janders.itunesapp.views

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewAnimationUtils
import android.view.WindowManager
import android.view.animation.AccelerateInterpolator
import com.janders.itunesapp.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        initializeView()
    }

    private fun initializeView() {
        splashView.visibility = View.VISIBLE

        val mHandler = Handler()
        mHandler.postDelayed({
            val cx = (splashView.left + splashView.right) / 2
            val cy = (splashView.top + splashView.bottom) / 2

            // get the final radius for the clipping circle
            val finalRadius = Math.max(splashView.width, splashView.height).toFloat()

            // create the animator for this imageInstruction (the start radius is zero)
            val anim = ViewAnimationUtils.createCircularReveal(splashView, cx, cy, 0f, finalRadius)
            anim.interpolator = AccelerateInterpolator()
            anim.duration = 1250

            // make the imageInstruction visible and start the animation
            anim.start()
        }, 50)

        val mHandler1 = Handler()
        mHandler1.postDelayed({
            /*goToActivity()
            finish()*/
        }, 1750)
    }

    private fun <T : Activity> goToActivity(classType: Class<T>) {
        startActivity(Intent(this, classType))
    }
}