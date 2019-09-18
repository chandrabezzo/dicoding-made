package com.bezzo.moviecatalogue.features.film


import android.os.Bundle
import android.view.View
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.extension.launchFragment
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.movie.MovieFragment
import com.bezzo.moviecatalogue.features.tvShow.TvShowFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_film.*
import org.koin.android.ext.android.inject

class FilmFragment : BaseFragment(), FilmViewContract {

    private val presenter: FilmPresenter<FilmViewContract> by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launchFragment(R.id.fl_movie, MovieFragment::class.java)

        tl_movie.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> launchFragment(R.id.fl_movie, MovieFragment::class.java)
                    1 -> launchFragment(R.id.fl_movie, TvShowFragment::class.java)
                }
            }
        })
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.fragment_film
    }
}
