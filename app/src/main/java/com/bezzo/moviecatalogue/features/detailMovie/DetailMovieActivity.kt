package com.bezzo.moviecatalogue.features.detailMovie

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
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.moviecatalogue.features.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.android.ext.android.inject

class DetailMovieActivity : BaseActivity() {

    lateinit var data: ResultMovie
    private val viewModel: FavoriteViewModel by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<ResultMovie>(AppConstant.DATA_MOVIE)?.let {
            data = it
        }

        viewModel.state.observe(this, favorite)

        val image = "https://image.tmdb.org/t/p/w185/${data.posterPath}"
        GlideApp.with(this).load(image).into(iv_banner)
        tv_title.text = data.title
        tv_release_date.text = data.releaseDate
        tv_user_score.text = data.voteAverage.toString()
        tv_desc.text = data.overview
        tv_popularity.text = data.popularity.toString()

        ib_favorite.setOnClickListener {
            val favorite = Favorite()
            favorite.id = data.id
            favorite.image = image
            favorite.title = data.title
            favorite.releaseDate = data.releaseDate
            favorite.userScore = data.voteAverage
            favorite.popularity = data.popularity
            favorite.desc = data.overview
            viewModel.addFavorite(favorite)
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail
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
