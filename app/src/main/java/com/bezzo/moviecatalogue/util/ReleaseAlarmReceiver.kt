package com.bezzo.moviecatalogue.util

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.bezzo.core.data.network.ApiCallback
import com.bezzo.core.data.network.RestApi
import com.bezzo.core.extension.toast
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.moviecatalogue.data.network.ApiEndpoint
import com.bezzo.moviecatalogue.features.main.MainActivity
import java.util.*

class ReleaseAlarmReceiver: BroadcastReceiver() {

    val EXTRA_MESSAGE = "message"

    val CHANNEL_ID = "channel2"
    val CHANNEL_NAME = "Movie Release Notification"

    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onReceive(context: Context, intent: Intent) {
        getRelease(object : ApiCallback<Movie> {
            override fun onResponse(data: Movie) {
                setRelease(data.results, context)
            }

            override fun onFailed(error: ANError) {
                context.toast("Request Released Movie Failed")
            }
        })
    }

    private fun getRelease(callback: ApiCallback<Movie>){
        val calendar = Calendar.getInstance()
        val today = "${calendar.get(Calendar.YEAR)}-${calendar.get(Calendar.MONTH)}-${calendar.get(
            Calendar.DAY_OF_MONTH)}"
        val url = "${ApiEndpoint.RELEASE}&primary_release_date.gte=$today&primary_release_date.lte=$today"
        RestApi.get(url, null, null, null)
            .getAsObject(Movie::class.java, object : ParsedRequestListener<Movie> {
                override fun onResponse(response: Movie) {
                    callback.onResponse(response)
                }

                override fun onError(anError: ANError) {
                    callback.onFailed(anError)
                }
            })
    }

    fun setRelease(values: MutableList<ResultMovie>, context: Context){
        for(movie in values){
            showAlarmNotif(context, context.getString(R.string.now_release), movie.title)
        }
    }

    private fun showAlarmNotif(context: Context, title: String, message: String){
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.icon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, R.drawable.icon))
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            builder.setChannelId(CHANNEL_ID)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    }

    fun setAlarm(context: Context, message: String){
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReleaseAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 8)
        calendar.set(Calendar.MINUTE, 0)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent)

        val alarmUp = PendingIntent.getBroadcast(context, 0,
            Intent(context, ReleaseAlarmReceiver::class.java),
            PendingIntent.FLAG_NO_CREATE) != null

        if(alarmUp){
            context.toast(context.getString(R.string.release_notif_active))
        }
        else {
            context.toast(context.getString(R.string.release_notif_disable))
        }
    }

    fun cancelAlarm(context: Context){
        alarmManager.cancel(pendingIntent)
        context.toast(context.getString(R.string.release_notif_disable))
    }
}