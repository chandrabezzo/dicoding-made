package com.bezzo.moviecatalogue.features.splash

import android.os.Bundle
import android.os.Handler
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.launchActivityClearAllStack
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.main.MainActivity

class SplashActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        Handler().postDelayed({
            launchActivityClearAllStack<MainActivity>()
        }, 3000L)
    }

    override fun setLayout(): Int {
        return R.layout.activity_splash
    }
}
