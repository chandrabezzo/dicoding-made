package com.bezzo.moviecatalogue.features.setting

import com.bezzo.core.base.BasePresenterContract
import com.bezzo.core.base.BaseViewContract

interface SettingViewContract: BaseViewContract {

}

interface SettingPresenterContract<V: SettingViewContract>: BasePresenterContract<V> {

}