package com.bezzo.moviecatalogue.features.detail

import com.bezzo.core.base.BaseActivityContract
import com.bezzo.core.base.BasePresenterContract

interface DetailViewContract: BaseActivityContract {

}

interface DetailPresenterContract<V: DetailViewContract>: BasePresenterContract<V> {

}