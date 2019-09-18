package com.bezzo.core.data.local

import io.reactivex.disposables.Disposable

abstract class LocalUpdateHandler<M> {
    abstract fun onUpdateSuccess(model: M)

    abstract fun onUpdateFailed(e: Throwable)

    abstract fun updateSubscribe(d: Disposable)
}

abstract class LocalsUpdateHandler<M> {
    abstract fun onUpdateSuccess(model: List<M>)

    abstract fun onUpdateFailed(e: Throwable)

    abstract fun onUpdateSubscribe(d: Disposable)
}