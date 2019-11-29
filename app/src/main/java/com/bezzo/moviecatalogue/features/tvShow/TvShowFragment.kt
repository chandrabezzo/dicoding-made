package com.bezzo.moviecatalogue.features.tvShow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.BaseFragment
import com.bezzo.core.base.Error
import com.bezzo.core.base.Loading
import com.bezzo.core.base.Receive
import com.bezzo.core.base.ViewModelState
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.TvShowRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import com.bezzo.moviecatalogue.features.detailTv.DetailTvActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.ext.android.inject

class TvShowFragment : BaseFragment() {

    private val viewModel: TvShowViewModel by inject()
    private val adapter: TvShowRVAdapter by inject()
    private val list = ArrayList<ResultTvShow>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_tv.layoutManager = layoutManager
            rv_tv.adapter = adapter
            viewModel.getTv()
            viewModel.state.observe(this, movies)

            adapter.setOnClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailTvActivity>{
                        putExtra(AppConstant.DATA_TV, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_tv_show
    }

    private val movies = Observer<ViewModelState> { state ->

        when(state){
            is Loading -> {
                rv_tv.hide()
                mb_retry.hide()
                pb_tv.show()
            }
            is Receive<*> -> {
                pb_tv.hide()
                rv_tv.show()
                mb_retry.hide()

                list.clear()
                list.addAll(state.data as MutableList<ResultTvShow>)
                adapter.setItem(list)
                adapter.notifyDataSetChanged()
            }
            is Error -> {
                context?.toast(state.message)

                mb_retry.show()
                pb_tv.hide()
                rv_tv.hide()
            }
        }
    }
}
