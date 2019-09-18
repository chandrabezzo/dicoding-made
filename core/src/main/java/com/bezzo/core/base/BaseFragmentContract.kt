package com.bezzo.core.base

import android.content.Context

/**
 * Created by bezzo on 21/12/17.
 */

interface BaseFragmentContract : BaseViewContract {

    fun getContext(): Context?

    fun onBackPressed()

    fun showProgressDialog(message: String)

    fun hideProgressDialog()
}

