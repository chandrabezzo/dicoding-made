package com.bezzo.moviecatalogue.features.about

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.base.Receive
import com.bezzo.core.base.ViewModelState
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Profile
import kotlinx.android.synthetic.main.fragment_about.*
import org.koin.android.ext.android.inject

class AboutFragment : BaseFragment() {

    private val viewModel: AboutViewModel by inject()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getProfile()
        viewModel.movieState.observe(this, profile)
    }

    override fun setLayout(): Int {
        return R.layout.fragment_about
    }
    private val profile = Observer<ViewModelState> {

        when(it){
            is Receive<*> -> {
                val data = it.data as Profile
                iv_profile.setImageResource(data.image)

                tv_nama.text = data.nama
                tv_email.text = data.email
            }
        }
    }
}
