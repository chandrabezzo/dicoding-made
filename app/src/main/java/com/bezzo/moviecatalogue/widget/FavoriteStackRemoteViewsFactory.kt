package com.bezzo.moviecatalogue.widget

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.local.LocalStorage
import com.bezzo.moviecatalogue.data.model.Favorite
import com.bezzo.moviecatalogue.data.network.FavoriteRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.util.concurrent.ExecutionException


class FavoriteStackRemoteViewsFactory(private var context: Context): RemoteViewsService.RemoteViewsFactory {

    val list: MutableList<Favorite> = ArrayList()
    lateinit var repository: FavoriteRepository

    override fun onCreate() {
        val dao = LocalStorage.getDatabase(context)
            .favoriteDao()
        repository = FavoriteRepository(dao)
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getItemId(p0: Int): Long = 0

    override fun onDataSetChanged() {
        list.clear()
        list.addAll(repository.allFavorite())
    }

    override fun hasStableIds(): Boolean = false

    override fun getViewAt(position: Int): RemoteViews {
        val remoteView = RemoteViews(context.packageName, R.layout.item_widget_favorite)

        try {
            val preview = Glide.with(context)
                .asBitmap()
                .load(list[position].image)
                .apply(RequestOptions().centerCrop())
                .submit()
                .get()
            remoteView.setImageViewBitmap(R.id.iv_poster, preview)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }

        remoteView.setTextViewText(R.id.tv_title, list[position].title)
        return remoteView
    }

    override fun getCount(): Int = list.size

    override fun getViewTypeCount(): Int = 1

    override fun onDestroy() {

    }
}