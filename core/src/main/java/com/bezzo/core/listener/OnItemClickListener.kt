package com.bezzo.core.listener

import android.view.View

/**
 * Created by bezzo on 22/12/17.
 */
interface OnItemClickListener {
    fun onItemClick(itemView: View, position: Int)

    fun onItemLongClick(itemView: View, position: Int) : Boolean
}