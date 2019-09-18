package com.bezzo.moviecatalogue.features.setting

import android.os.Bundle
import com.bezzo.core.base.BaseActivity
import com.bezzo.moviecatalogue.R
import org.koin.android.ext.android.inject

class SettingActivity : BaseActivity(), SettingViewContract {

    private val presenter: SettingPresenter<SettingViewContract> by inject()

    override fun onInitializedView(savedInstanceState: Bundle?) {
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun setLayout(): Int {
        return R.layout.activity_setting
    }
}
