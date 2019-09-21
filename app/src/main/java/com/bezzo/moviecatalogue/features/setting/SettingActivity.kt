package com.bezzo.moviecatalogue.features.setting

import android.app.Activity
import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.core.util.LocaleUtil
import com.bezzo.moviecatalogue.R
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
    }

    override fun setLayout(): Int {
        return R.layout.activity_setting
    }

    override fun onClickBack() {
        setResult(Activity.RESULT_OK)
        finish()
    }
}
