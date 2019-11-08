package com.bezzo.favoritemovie

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.GlideApp
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_favorite.*

class DetailFavoriteActivity : BaseActivity() {

    lateinit var data: FavoriteModel

    override fun onInitializedView(savedInstanceState: Bundle?) {
        data = Gson().fromJson<FavoriteModel>(dataReceived?.getString(AppConstant.DATA_FAVORITE),
            FavoriteModel::class.java)

        GlideApp.with(this).load(data.image).into(iv_banner)
        tv_title.text = data.title
        tv_release_date.text = data.releaseDate
        tv_user_score.text = data.userScore.toString()
        tv_desc.text = data.desc
        tv_popularity.text = data.popularity.toString()

        ib_remove.setOnClickListener {
            data.id?.let {

            }
            finish()
        }
    }

    override fun setLayout(): Int {
        return R.layout.activity_detail_favorite
    }
}
