 package com.bezzo.moviecatalogue.features.favorite


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
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.adapter.FavoriteRvAdapter
import com.bezzo.moviecatalogue.constanta.AppConstant
import com.bezzo.moviecatalogue.data.model.Favorite
import kotlinx.android.synthetic.main.fragment_favorite.*
import org.koin.android.ext.android.inject

 class FavoriteFragment : BaseFragment() {

    private val viewModel: FavoriteViewModel by inject()
    private val adapter: FavoriteRvAdapter by inject()
    private val list : MutableList<Favorite> = ArrayList()

    override fun onViewInitialized(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorite()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        context?.let {
            val layoutManager = LinearLayoutManager(it)
            rv_favorite.layoutManager = layoutManager
            rv_favorite.adapter = adapter
            viewModel.getFavorite()
            viewModel.state.observe(this, favorites)

            adapter.setOnClick(object : OnItemClickListener {
                override fun onItemClick(itemView: View, position: Int) {
                    launchActivity<DetailFavoriteActivity>{
                        putExtra(AppConstant.DATA_FAVORITE, list[position])
                    }
                }

                override fun onItemLongClick(itemView: View, position: Int): Boolean {
                    return true
                }
            })
        }
    }

    override fun setLayout(): Int {
        return R.layout.fragment_favorite
    }

    private val favorites = Observer<ViewModelState> {
        when(it){
            is Loading -> {
               pb_favorite.show()
                rv_favorite.hide()
                tv_empty.hide()
            }
            is Receive<*> -> {
                pb_favorite.hide()
                rv_favorite.show()
                tv_empty.hide()

                list.clear()
                list.addAll(it.data as MutableList<Favorite>)
                adapter.setItem(list)
                adapter.notifyDataSetChanged()
            }
            is Error -> {

            }
            is Empty -> {
                pb_favorite.hide()
                rv_favorite.hide()
                tv_empty.show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
        val mSearchmenuItem = menu.findItem(R.id.nav_search)
        val searchView = mSearchmenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val listSearch = ArrayList<Favorite>()
                listSearch.addAll(list.filter { it.title.contains(query.toString()) })
                adapter.setItem(listSearch)
                adapter.notifyDataSetChanged()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                val listSearch = ArrayList<Favorite>()
                listSearch.addAll(list.filter { it.title.contains(query.toString()) })
                adapter.setItem(listSearch)
                adapter.notifyDataSetChanged()
                return true
            }
        })
    }
}
