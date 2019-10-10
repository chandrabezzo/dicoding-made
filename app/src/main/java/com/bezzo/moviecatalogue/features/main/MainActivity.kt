package com.bezzo.moviecatalogue.features.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.launchFragment
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.about.AboutFragment
import com.bezzo.moviecatalogue.features.favorite.FavoriteFragment
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
                R.id.nav_favorite -> launchFragment(R.id.fl_main, FavoriteFragment::class.java)
                R.id.nav_about -> launchFragment(R.id.fl_main, AboutFragment::class.java)
            }

            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.setting_menu, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
//        when(item?.itemId){
//            R.id.nav_setting -> {
//                val intent = Intent(this, SettingActivity::class.java)
//                startActivityForResult(intent, 0)
//            }
//        }
//
//        return true
//    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        recreate()
    }
}
