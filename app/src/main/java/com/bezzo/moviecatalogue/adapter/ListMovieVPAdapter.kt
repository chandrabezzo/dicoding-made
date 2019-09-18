package com.bezzo.moviecatalogue.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.movie.MovieFragment
import com.bezzo.moviecatalogue.features.tvShow.TvShowFragment


class ListMovieVPAdapter(val context: Context, fragmentManager: FragmentManager)
    : FragmentStatePagerAdapter(fragmentManager) {

    val PAGE_COUNT = 2
    var tabTitles = arrayOf(R.string.movie, R.string.tv_show)

    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()

        when(position){
            0 -> fragment = MovieFragment()
            1 -> fragment = TvShowFragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return PAGE_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return tabTitles[position].toString()
    }
}