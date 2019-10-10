package com.bezzo.moviecatalogue.features.tvShow

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bezzo.core.base.*
import com.bezzo.core.extension.hide
import com.bezzo.core.extension.launchActivity
import com.bezzo.core.extension.show
import com.bezzo.core.extension.toast
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.TvShowRVAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.ResultMovie
import com.bezzo.moviecatalogue.data.model.ResultTvShow
import com.bezzo.moviecatalogue.features.detailTv.DetailTvActivity
import com.bezzo.moviecatalogue.features.main.MainActivity
import kotlinx.android.synthetic.main.fragment_tv_show.*
import org.koin.android.ext.android.inject

class TvShowFragment : BaseFragment() {

    private val viewModel: TvShowViewModel by inject()
    private val adapter: TvShowRVAdapter by inject()
    private val list = ArrayList<ResultTvShow>()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val mSearchmenuItem = menu.findItem(R.id.nav_search)
        val searchView = mSearchmenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val listSearch = ArrayList<ResultTvShow>()
                listSearch.addAll(list.filter { it.name.contains(query.toString()) })
                adapter.setItem(listSearch)
                adapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val listSearch = ArrayList<ResultTvShow>()
                listSearch.addAll(list.filter { it.name.contains(query.toString()) })
                adapter.setItem(listSearch)
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }
}
