package com.bezzo.favoritemovie

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), LoaderManager.LoaderCallbacks<Cursor> {

    lateinit var adapter: FavoriteRvAdapter
    private var list : MutableList<FavoriteModel> = ArrayList()
    val LOADER_FAVORITE = 110
    val AUTHORITY = "com.bezzo.moviecatalogue.provider"
    val CONTENT_URI = Uri.Builder().scheme("content")
        .authority(AUTHORITY)
        .appendEncodedPath(AppConstant.FAVORITE)
        .build()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        val layoutManager = LinearLayoutManager(this)
        adapter = FavoriteRvAdapter(this, list)
        rv_favorite.layoutManager = layoutManager
        rv_favorite.adapter = adapter

        adapter.setOnClick(object : OnItemClickListener {
            override fun onItemClick(itemView: View, position: Int) {
                launchActivity<DetailFavoriteActivity>{
                    putExtra(AppConstant.DATA_FAVORITE, Gson().toJson(list[position]))
                }
            }

            override fun onItemLongClick(itemView: View, position: Int): Boolean {
                return true
            }
        })

        supportLoaderManager.initLoader(LOADER_FAVORITE, null, this)
    }

    override fun setLayout(): Int {
        return R.layout.activity_main
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        return CursorLoader(this, CONTENT_URI, null,
            null, null, null)
    }

    override fun onLoaderReset(loader: Loader<Cursor>) {

    }

    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        try {
            list.clear()
            list = getItem(data!!)
            adapter.setItem(list)
            adapter.notifyDataSetChanged()

            if(list.isEmpty()){
                tv_empty.show()
                rv_favorite.hide()
            }
            else {
                tv_empty.hide()
                rv_favorite.show()
            }
        } catch (e: Exception){
            toast("Harap install movie catalogue terlebih dahulu")
            finish()
        }
    }

    private fun getItem(cursor: Cursor): ArrayList<FavoriteModel> {
        val list = ArrayList<FavoriteModel>()
        cursor.moveToFirst()
        if(cursor.count > 0){
            do {
                val favorite = FavoriteModel(cursor)
                list.add(favorite)
                cursor.moveToNext()
            } while (cursor.isAfterLast.not())
        }
        return list
    }
}
