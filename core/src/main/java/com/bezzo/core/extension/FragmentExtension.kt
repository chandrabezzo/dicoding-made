package com.bezzo.core.extension

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.bezzo.core.R

//inline fun <reified T : Any> Fragment.launchActivity(noinline init: Intent.() -> Unit = {}) {
//    activity?.let {
//        val intent = newIntent<T>(it)
//        intent.init()
//        startActivity(intent)
//        it.overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out)
//    }
//}

inline fun <reified T : Any> Fragment.launchActivity(enterAnimation: Int, exitAnimation: Int,
                                                     noinline init: Intent.() -> Unit = {}) {
    activity?.let {
        val intent = newIntent<T>(it)
        intent.init()
        startActivity(intent)
        //to up -> stay
        it.overridePendingTransition(enterAnimation, exitAnimation)
    }
}

//inline fun <reified T : Any> Fragment.launchActivity(
//        requestCode: Int = -1,
//        noinline init: Intent.() -> Unit = {}) {
//
//    activity?.let {
//        val intent = newIntent<T>(it)
//        intent.init()
//        startActivityForResult(intent, requestCode)
//        it.overridePendingTransition(R.anim.slide_in_from_right, R.anim.scale_out)
//    }
//}

inline fun <reified T : Any> Fragment.launchActivity(
        options: Bundle? = null,
        noinline init: Intent.() -> Unit = {}) {

    val intent = newIntent<T>(activity!!)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

inline fun Fragment.launchFragment(contentReplace: Int, classFragment: Class<*>,
                                            noinline init: Bundle.() -> Unit = {}){
    var fragment: Fragment? = null

    try {
        fragment = classFragment.newInstance() as Fragment
    } catch (e: InstantiationException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    }

    val transaction = activity?.supportFragmentManager?.beginTransaction()

    val data = Bundle()
    data.init()
    fragment?.arguments = data

    fragment?.let { transaction?.replace(contentReplace, it) }

    transaction?.commit()
}

inline fun Fragment.launchDialog(classDialog: Class<*>,
                                          noinline init: Bundle.() -> Unit = {}){
    val fm = activity?.supportFragmentManager
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