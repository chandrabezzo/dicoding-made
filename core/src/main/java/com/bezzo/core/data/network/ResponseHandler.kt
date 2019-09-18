package com.bezzo.core.data.network

import com.bezzo.core.base.BaseResponse
import io.reactivex.functions.Consumer

/**
 * Created by bezzo on 19/10/17.
 */

abstract class ResponseHandler<M : BaseResponse> constructor(private val successCode : Int) : Consumer<M> {

    abstract fun onSuccess(model : M)

    abstract fun onUnauthorized()

    abstract fun onError(model : M)

    override fun accept(model : M) {
        when {
            model.status == successCode -> onSuccess(model)
            model.status == 401 -> onUnauthorized()
            else -> onError(model)
        }
    }
}