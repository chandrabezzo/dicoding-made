package com.bezzo.moviecatalogue.features.film

import com.bezzo.core.base.BaseFragmentContract
import com.bezzo.core.base.BasePresenterContract

interface FilmViewContract: BaseFragmentContract {

}

interface FilmPresenterContract<V: FilmViewContract>: BasePresenterContract<V> {

}