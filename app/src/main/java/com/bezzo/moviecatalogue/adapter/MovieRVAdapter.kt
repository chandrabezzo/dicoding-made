package com.bezzo.moviecatalogue.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bezzo.moviecatalogue.R
import com.bezzo.moviecatalogue.data.model.Movie
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import kotlinx.android.synthetic.main.item_rv_movie.view.*

class MovieRVAdapter constructor(private val context: Context, private val list: ArrayList<Movie>)
    : RecyclerView.Adapter<MovieRVAdapter.Item>(){

    lateinit var listener: OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(LayoutInflater.from(parent.context).inflate(R.layout.item_rv_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: ArrayList<Movie>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<Movie>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: Movie) {
            itemView.tv_judul.text = "${model.title} (${model.releaseYear})"
            itemView.tv_desc.text = "${model.userScore} - ${model.genre}"
            GlideApp.with(context).load(model.image).into(itemView.iv_profile)
        }
    }
}