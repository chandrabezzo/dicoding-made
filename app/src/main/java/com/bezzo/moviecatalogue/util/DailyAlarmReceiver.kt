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
import com.bezzo.core.extension.toast
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.features.main.MainActivity
import java.util.*

class DailyAlarmReceiver: BroadcastReceiver() {

    val EXTRA_MESSAGE = "message"
    val NOTIFICATION_ID = 1
    val CHANNEL_ID = "channel1"
    val CHANNEL_NAME = "Movie Directory Notification"

    lateinit var alarmManager: AlarmManager
    lateinit var pendingIntent: PendingIntent

    override fun onReceive(context: Context, intent: Intent) {
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val title = context.resources.getString(R.string.app_name)
        showAlarmNotif(context, title, message)
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

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    fun setAlarm(context: Context, message: String){
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, DailyAlarmReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

        val calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY, 7)
        calendar.set(Calendar.MINUTE, 0)

        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, pendingIntent)

        val alarmUp = PendingIntent.getBroadcast(context, 0,
                Intent(context, DailyAlarmReceiver::class.java),
                PendingIntent.FLAG_NO_CREATE) != null

        if(alarmUp){
            context.toast(context.getString(R.string.daily_notif_active))
        }
        else {
            context.toast(context.getString(R.string.daily_notif_disable))
        }
    }

    fun cancelAlarm(context: Context){
        alarmManager.cancel(pendingIntent)
        context.toast(context.getString(R.string.daily_notif_disable))
    }
}