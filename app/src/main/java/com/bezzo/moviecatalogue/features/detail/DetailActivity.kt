package com.bezzo.moviecatalogue.features.detail

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailActivity : BaseActivity(), DetailViewContract {

    private val presenter: DetailPresenter<DetailViewContract> by inject()
    lateinit var data: Movie

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)

        dataReceived?.getParcelable<Movie>(AppConstant.DATA_MOVIE)?.let {
            data = it
        }

        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        GlideApp.with(this).load(data.image).into(iv_profile)
        tv_judul.text = "${data.judul} (${data.tahunRilis})"
        tv_durasi.text = data.durasi
        tv_genre.text = data.genre
        tv_user_score.text = data.userScore
        tv_deskripsi.text = data.deskripsi
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail
    }
}
