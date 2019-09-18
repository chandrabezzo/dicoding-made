package com.bezzo.core.extension

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bezzo.core.R
import com.bezzo.core.util.AppLoggerUtil
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability

inline fun <reified T : Any> Activity.launchActivity(isFinish : Boolean, noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent)
    if (isFinish) finish()
    overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out)
}

inline fun <reified T : Any> Activity.launchActivity(enterAnimation : Int, exitAnimation : Int,
                                                     isFinish: Boolean,
                                                     noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent)
    if (isFinish) finish()
    //to up -> stay
    overridePendingTransition(enterAnimation, exitAnimation)
}

inline fun <reified T : Any> Activity.launchActivityClearAllStack(noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out)
    finish()
}

inline fun <reified T : Any> Activity.launchActivityClearAllStack(enterAnimation : Int, exitAnimation : Int,
                                                     noinline init: Intent.() -> Unit = {}) {
    val intent = newIntent<T>(this)
    intent.init()
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    startActivity(intent)
    //to up -> stay
    overridePendingTransition(enterAnimation, exitAnimation)
    finish()
}

inline fun <reified T : Any> Activity.launchActivity(
        requestCode: Int = -1,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    startActivityForResult(intent, requestCode)
    overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out);
}

inline fun <reified T : Any> Activity.launchActivity(
        requestCode: Int = -1,
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}

inline fun <reified T : Any> Context.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
        Intent(context, T::class.java)

fun Context.share(url: String) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, url)
    startActivity(Intent.createChooser(shareIntent, "Share link using"))
}

fun Activity.setOk() {
    setResult(RESULT_OK)
    finish()
    overridePendingTransition(R.anim.scale_in, R.anim.slide_out_to_right)
}

fun Activity.setOk(enterAnimation: Int, exitAnimation: Int) {
    setResult(RESULT_OK)
    finish()
    overridePendingTransition(enterAnimation, exitAnimation)
}

fun Activity.shareForResult(url: String, code: Int = 100) {
    val shareIntent = Intent(Intent.ACTION_SEND)
    shareIntent.type = "text/plain"
    shareIntent.putExtra(Intent.EXTRA_TEXT, url)
    startActivityForResult(Intent.createChooser(shareIntent, "Share link using"), code)
}

inline fun AppCompatActivity.launchFragment(contentReplace: Int, classFragment: Class<*>,
                                            noinline init: Bundle.() -> Unit = {}){
    var fragment: Fragment? = null

    try {
        fragment = classFragment.newInstance() as Fragment
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val transaction = this.supportFragmentManager.beginTransaction()

    val data = Bundle()
    data.init()
    fragment?.arguments = data

    fragment?.let { transaction.replace(contentReplace, it) }

    transaction.commit()
}

inline fun AppCompatActivity.launchDialog(classDialog: Class<*>,
                                          noinline init: Bundle.() -> Unit = {}){
    val fm = supportFragmentManager
    var fragment: DialogFragment? = null

    try {
        fragment = classDialog.newInstance() as DialogFragment
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val data = Bundle()
    data.init()
    fragment?.arguments = data

    fragment?.show(fm, "TAG")
}

fun Activity.checkPlayService() : Boolean {
    val apiAvailability = GoogleApiAvailability.getInstance()
    val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)
    if (resultCode != ConnectionResult.SUCCESS) {
        if (apiAvailability.isUserResolvableError(resultCode)) {
            apiAvailability.getErrorDialog(this, resultCode, 0).show()
        } else {
            AppLoggerUtil.e(this.getString(R.string.not_support_play_service))
            toast(getString(R.string.device_not_support), Toast.LENGTH_SHORT)
            finish()
        }
        return false
    }
    return true
}

fun Activity.isServiceRunning(serviceClass: Class<*>) : Boolean {
    val manager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}