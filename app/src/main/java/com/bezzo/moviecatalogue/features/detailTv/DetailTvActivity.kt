package com.bezzo.moviecatalogue.features.detailTv

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.TvShow
import kotlinx.android.synthetic.main.activity_detail.*

class DetailTvActivity : BaseActivity() {

    lateinit var data: TvShow

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<TvShow>(AppConstant.DATA_TV)?.let {
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
        tv_judul.text = "${data.name} (${data.firstAirDate})"
        tv_user_score.text = data.voteAverage.toString()
        tv_popularity.text = data.popularity.toString()
        tv_deskripsi.text = data.overview
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_tv
    }
}
