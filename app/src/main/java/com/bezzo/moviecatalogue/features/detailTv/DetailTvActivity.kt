package com.bezzo.moviecatalogue.features.detailTv

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.base.ViewModelState
import com.bezzo.core.extension.toast
import com.bezzo.core.util.GlideApp
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Favorite
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import com.bezzo.moviecatalogue.features.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailTvActivity : BaseActivity() {

    lateinit var data: ResultTvShow
    private val viewModel: FavoriteViewModel by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<ResultTvShow>(AppConstant.DATA_TV)?.let {
            data = it
        }

        val image = "https://image.tmdb.org/t/p/w185/${data.posterPath}"
        GlideApp.with(this).load(image).into(iv_banner)
        tv_title.text = data.name
        tv_release_date.text = data.firstAirDate
        tv_user_score.text = data.voteAverage.toString()
        tv_popularity.text = data.popularity.toString()
        tv_desc.text = data.overview

        viewModel.state.observe(this, favorite)

        ib_favorite.setOnClickListener {
            val favorite = Favorite(
                data.id,
                image,
                data.name,
                data.firstAirDate,
                data.voteAverage,
                data.popularity,
                data.overview
            )
            viewModel.addFavorite(favorite)
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_tv
    }

    private val favorite = Observer<ViewModelState> {

        when(it){
            is Loading -> {

            }
            is Receive<*> -> {
                toast(it.data as String)
            }
            is Error -> {
                it.message?.let { message -> toast(message) }
            }
        }
    }
}
