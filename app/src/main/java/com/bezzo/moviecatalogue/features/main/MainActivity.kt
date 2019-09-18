package com.bezzo.moviecatalogue.features.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.about.AboutFragment
import com.bezzo.moviecatalogue.features.movie.MovieFragment
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.launchFragment
import com.bezzo.moviecatalogue.features.film.FilmFragment
import com.bezzo.moviecatalogue.features.setting.SettingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        setActionBarTitle(getString(R.string.app_name))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        launchFragment(R.id.fl_main, FilmFragment::class.java)

        bnv_main.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_movie -> launchFragment(R.id.fl_main, FilmFragment::class.java)
                R.id.nav_about -> launchFragment(R.id.fl_main, AboutFragment::class.java)
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.nav_setting -> {
                launchActivity<SettingActivity>()
            }
        }

        return true
    }
}
