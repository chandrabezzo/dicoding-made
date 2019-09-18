package com.bezzo.core.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by bezzo on 21/12/17.
 */

abstract class BaseHolder<M>(itemView: View)
    : RecyclerView.ViewHolder(itemView) {

    var model: M? = null
        set(model) {
            field = model
            if (model != null) {
                setContent(model)
            }
        }

    abstract fun setContent(model: M)
}