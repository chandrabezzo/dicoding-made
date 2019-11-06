package com.bezzo.moviecatalogue.widget

import android.content.Intent
import android.widget.RemoteViewsService

class StackWidgetService: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return FavoriteRemoteViewsFactory(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
    }
}