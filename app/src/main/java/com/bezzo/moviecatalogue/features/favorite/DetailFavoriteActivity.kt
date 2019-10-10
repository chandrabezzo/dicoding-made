package com.bezzo.moviecatalogue.features.favorite

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
import kotlinx.android.synthetic.main.activity_detail.iv_banner
import kotlinx.android.synthetic.main.activity_detail.tv_desc
import kotlinx.android.synthetic.main.activity_detail.tv_popularity
import kotlinx.android.synthetic.main.activity_detail.tv_release_date
import kotlinx.android.synthetic.main.activity_detail.tv_title
import kotlinx.android.synthetic.main.activity_detail.tv_user_score
import kotlinx.android.synthetic.main.activity_detail_favorite.*
import org.koin.android.ext.android.inject

class DetailFavoriteActivity : BaseActivity() {

    lateinit var data: Favorite
    private val viewModel: FavoriteViewModel by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        dataReceived?.getParcelable<Favorite>(AppConstant.DATA_FAVORITE)?.let {
            data = it
        }

        viewModel.state.observe(this, favorite)

        GlideApp.with(this).load(data.image).into(iv_banner)
        tv_title.text = data.title
        tv_release_date.text = data.releaseDate
        tv_user_score.text = data.userScore.toString()
        tv_desc.text = data.desc
        tv_popularity.text = data.popularity.toString()

        ib_remove.setOnClickListener {
            viewModel.remove(data.id)
            finish()
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_favorite
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
