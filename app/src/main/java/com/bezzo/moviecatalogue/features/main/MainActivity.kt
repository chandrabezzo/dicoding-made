package com.bezzo.moviecatalogue.features.main

import android.os.Bundle
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.about.AboutFragment
import com.bezzo.moviecatalogue.features.movie.MovieFragment
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.launchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        launchFragment(R.id.fl_main, MovieFragment::class.java)

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_movie -> launchFragment(R.id.fl_main, MovieFragment::class.java)
                R.id.nav_about -> launchFragment(R.id.fl_main, AboutFragment::class.java)
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }
}
