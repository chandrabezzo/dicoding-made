package com.bezzo.moviecatalogue.features.setting

import android.app.Activity
import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.data.session.SessionConstants
import com.bezzo.core.data.session.SessionHelper
import com.bezzo.core.util.LocaleUtil
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.util.DailyAlarmReceiver
import com.bezzo.moviecatalogue.util.ReleaseAlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*
import org.koin.android.ext.android.inject

class SettingActivity : BaseActivity() {

    override fun onInitializedView(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar)
        mActionBar = supportActionBar
        displayHome()
        setActionBarTitle(getString(R.string.app_name))
        toolbar.setNavigationOnClickListener {
            onNavigationClick()
        }

        val dailyReminder = DailyAlarmReceiver()
        val releaseReminder = ReleaseAlarmReceiver()

        if(LocaleUtil.getLanguage(this) == "en"){
            rb_indonesia.isChecked = false
            rb_english.isChecked = true
        }
        else {
            rb_indonesia.isChecked = true
            rb_english.isChecked = false
        }

        rb_indonesia.setOnCheckedChangeListener { _, value ->
            if(value){
                LocaleUtil.setLocale(this, "in")
            }
            else {
                LocaleUtil.setLocale(this, "en")
            }
        }

        rb_english.setOnCheckedChangeListener { _, value ->
            if(value){
                LocaleUtil.setLocale(this, "en")
            }
            else {
                LocaleUtil.setLocale(this, "in")
            }
        }

        sc_daily.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                dailyReminder.setAlarm(this, getString(R.string.daily_notif))
                SessionHelper().addSession(SessionConstants.DAILY, true)
            }
            else {
                dailyReminder.cancelAlarm(this)
                SessionHelper().addSession(SessionConstants.DAILY, false)
            }
        }

        sc_release.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                releaseReminder.setAlarm(this, getString(R.string.release_notif))
                SessionHelper().addSession(SessionConstants.RELEASE, true)
            }
            else {
                releaseReminder.cancelAlarm(this)
                SessionHelper().addSession(SessionConstants.RELEASE, false)
            }
        }

        sc_daily.isChecked = SessionHelper().getSession(SessionConstants.DAILY, false) != false
        sc_release.isChecked = SessionHelper().getSession(SessionConstants.RELEASE, false) != false
    }

    override fun setLayout(): Int {
        return R.layout.activity_setting
    }

    override fun onClickBack() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
