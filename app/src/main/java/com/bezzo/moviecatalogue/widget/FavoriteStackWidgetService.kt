package com.bezzo.moviecatalogue.widget

import android.content.Intent
import android.widget.RemoteViewsService

class FavoriteStackWidgetService: RemoteViewsService() {

    override fun onGetViewFactory(intent: Intent): RemoteViewsFactory
            = FavoriteStackRemoteViewsFactory(applicationContext)
}