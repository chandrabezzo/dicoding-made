package com.bezzo.core.extension

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.widget.Toast
import androidx.annotation.StringRes
import com.bezzo.core.util.LocaleUtil
import com.bezzo.core.util.PermissionUtil
import java.util.*

fun Context.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}

fun Context.toast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, getString(resId), length).show()
}

fun Context.isWifi(type: Int): Boolean {
    return type == ConnectivityManager.TYPE_WIFI
}

fun Context.isBroadband(type: Int): Boolean {
    return type == ConnectivityManager.TYPE_MOBILE
}

fun Context.isConnected() : Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun Context.getConnectionManager(): ConnectivityManager? {
    return this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
}

@SuppressLint("MissingPermission")
fun Context.getNetworkInfo(connectivityManager: ConnectivityManager): NetworkInfo? {
    return if (PermissionUtil.requestAccessNetworkState(this)) {
        connectivityManager.activeNetworkInfo
    } else {
        getNetworkInfo(connectivityManager)
        null
    }
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE)
    return if (connectivityManager is ConnectivityManager) {
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        networkInfo?.isConnected ?: false
    } else false
}

fun Context.getColor(id: Int): Int {
    return if (Build.VERSION.SDK_INT >= 23) {
        this.resources.getColor(id, this.theme)
    } else this.resources.getColor(id)
}

fun Context.getDrawable(id: Int): Drawable {
    return if (Build.VERSION.SDK_INT >= 21) {
        this.resources.getDrawable(id, this.theme)
    } else this.resources.getDrawable(id)
}

fun Context.changeLanguage(language: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        LocaleUtil.setLocale(this, language)
    } else {
        val res = this.resources
        val dm = res.displayMetrics
        val configuration = res.configuration
        configuration.setLocale(Locale(language))
        res.updateConfiguration(configuration, dm)
    }
}