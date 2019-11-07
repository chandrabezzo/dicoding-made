package com.bezzo.favoritemovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bezzo.core.base.BaseHolder
import com.bezzo.core.listener.OnItemClickListener
import com.bezzo.core.util.GlideApp
import kotlinx.android.synthetic.main.item_rv_movie.view.*

class FavoriteRvAdapter internal constructor(private val context: Context,
                                             private val list: MutableList<FavoriteModel>)
    : RecyclerView.Adapter<FavoriteRvAdapter.Item>(){

    lateinit var listener: OnItemClickListener

    fun setOnClick(listener: OnItemClickListener){
        this.listener = listener
    }

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Item {
        return Item(inflater.inflate(R.layout.item_rv_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: Item, position: Int) {
        holder.model = list[position]
    }

    fun setItem(values: MutableList<FavoriteModel>){
        list.clear()
        list.addAll(values)
    }

    inner class Item(itemView: View): BaseHolder<FavoriteModel>(itemView){

        init {
            itemView.setOnClickListener { listener.onItemClick(it, layoutPosition) }
        }

        override fun setContent(model: FavoriteModel) {
            itemView.tv_judul.text = String.format(context.getString(R.string.judul_name),
                model.title, model.releaseDate)
            itemView.tv_desc.text = "${model.userScore}"
            GlideApp.with(context).load(model.image).into(itemView.iv_profile)
        }
    }
}