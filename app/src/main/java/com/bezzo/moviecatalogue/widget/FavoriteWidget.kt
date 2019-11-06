package com.bezzo.moviecatalogue.widget

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.RemoteViews
import com.bezzo.moviecatalogue.R



class FavoriteWidget: AppWidgetProvider() {

    companion object {
        fun updateWidget(context: Context, appWidgetManager: AppWidgetManager,
                         appWidgetId: Int){
            val intent = Intent(context, StackWidgetService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME))

            val views = RemoteViews(context.packageName, R.layout.widget_favorite)
            views.setRemoteAdapter(R.id.favoriteWidgetStackView, intent)
            views.setEmptyView(R.id.favoriteWidgetStackView, R.id.favoriteWidgetEmptyView)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId: Int in appWidgetIds) {
            updateWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context?) {

    }

    override fun onDisabled(context: Context?) {

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
    }
}