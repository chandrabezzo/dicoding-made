package com.bezzo.moviecatalogue.features.detailMovie

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultMovie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailMovieActivity : BaseActivity() {

    lateinit var data: ResultMovie

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<ResultMovie>(AppConstant.DATA_MOVIE)?.let {
            data = it
        }

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        val image = "https://image.tmdb.org/t/p/w185/${data.posterPath}"
        GlideApp.with(this).load(image).into(iv_poster)
        tv_judul.text = "${data.title} (${data.releaseDate})"
        tv_user_score.text = data.voteAverage.toString()
        tv_deskripsi.text = data.overview
        tv_popularity.text = data.popularity.toString()
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail
    }
}
