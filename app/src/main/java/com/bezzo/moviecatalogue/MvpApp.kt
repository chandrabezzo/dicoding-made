package com.bezzo.moviecatalogue

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.androidnetworking.AndroidNetworking
import com.bezzo.moviecatalogue.di.allModule
import com.bezzo.core.CoreModul
import com.bezzo.core.util.AppLoggerUtil
import com.bezzo.core.util.LocaleUtil
import com.orhanobut.hawk.Hawk
import org.koin.android.ext.android.startKoin

class MvpApp : Application() {

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleUtil.onAttach(base, LocaleUtil.getLanguage(base)))

        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        startKoin(this, allModule)

        CoreModul.Builder(this).build()
        AppLoggerUtil.init()
        Hawk.init(this).build()
        AndroidNetworking.initialize(applicationContext)
    }
}