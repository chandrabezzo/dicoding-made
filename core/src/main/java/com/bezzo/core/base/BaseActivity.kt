package com.bezzo.core.base

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bezzo.core.BuildConfig
import com.bezzo.core.R
import com.bezzo.core.extension.AppExtensions
import com.bezzo.core.extension.toast
import com.bezzo.core.util.LocaleUtil
import kotlinx.android.synthetic.main.default_toolbar.*

/**
 * Created by bezzo on 26/09/17.
 */

abstract class BaseActivity : AppCompatActivity(), BaseActivityContract, BaseFragment.Callback {

    var mActionBar: ActionBar? = null
    var dataReceived: Bundle? = null
    lateinit var mContext: Context
    protected var isAnimEnabled = true
    private var mProgressDialog: AlertDialog? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayout())
        mContext = this
        dataReceived = intent.extras

        setSupportActionBar(default_toolbar)

        mActionBar = supportActionBar

//        if (default_toolbar != null){
//            default_toolbar.setNavigationOnClickListener(View.OnClickListener { view: View ->
//                onNavigationClick()
//            })
//        }

        if (intent != null) {
            dataReceived = intent.extras
        }

        onInitializedView(savedInstanceState)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LocaleUtil.onAttach(newBase))
    }

    protected abstract fun onInitializedView(savedInstanceState: Bundle?)

    fun onNavigationClick() {
        onClickBack()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun hasPermission(permission: String): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    override fun onFragmentAttached() {

    }

    override fun onFragmentDetached(TAG: String) {

    }

    abstract fun setLayout() : Int

    override fun showProgressDialog(message: String) {
        mProgressDialog = AppExtensions.showProgressDialog(this, message)
        mProgressDialog?.show()
    }

    override fun hideProgressDialog() {
        mProgressDialog?.dismiss()
    }

    override fun getContext(): Context? {
        return mContext
    }

    override fun displayHome() {
        if (mActionBar != null) {
            mActionBar?.setHomeButtonEnabled(true)
            mActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_white)
            mActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun setActionBarTitle(title: String) {
        if (mActionBar != null) {
            mActionBar?.title = title
        }
    }

    override fun onClickBack() {
        finish()
        if (isAnimEnabled || Build.VERSION.SDK_INT < 21){
            overridePendingTransition(R.anim.scale_in, R.anim.slide_out_to_right)
        }
    }

    override fun handleError(case: Int, message : String) {
        var error = ""
        when(case) {
            1 -> { error = getString(R.string.service_not_found) }
            2 -> { error = getString(R.string.network_not_stable) }
            3 -> { error = getString(R.string.server_error) }
            4 -> { error = getString(R.string.service_not_connected) }
            5 -> { error = getString(R.string.some_error) }
            6 -> { error = message }
        }

        if (BuildConfig.DEBUG){
            toast(message, Toast.LENGTH_SHORT)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (isAnimEnabled || Build.VERSION.SDK_INT < 21){
            overridePendingTransition(R.anim.scale_in, R.anim.slide_out_to_right)
        }
    }
}
