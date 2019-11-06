package com.bezzo.moviecatalogue.widget

import android.content.Context
import android.database.Cursor
import android.os.Binder
import android.widget.AdapterView
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.bezzo.moviecatalogue.data.model.Favorite

class FavoriteRemoteViewsFactory(private var context: Context): RemoteViewsService.RemoteViewsFactory {

    private var cursor: Cursor? = null

    override fun onCreate() {

    }

    override fun getLoadingView(): RemoteViews? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return if (cursor?.moveToPosition(position) == true) cursor?.getLong(0) ?: 0 else position.toLong()
    }

    override fun onDataSetChanged() {
        cursor?.close()

    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getViewAt(position: Int): RemoteViews? {
        if (position == AdapterView.INVALID_POSITION ||
            cursor == null || cursor?.moveToPosition(position) == false
        ) {
            return null
        }

        return null
    }

    override fun getCount(): Int {
        return if (cursor == null) 0 else cursor?.count ?: 0
    }

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun onDestroy() {
        cursor?.close()
    }

//    private fun getItem(position: Int): Favorite {
//        check(cursor?.moveToPosition(position)?.not() != true) { "Position invalid!" }
//    }
}