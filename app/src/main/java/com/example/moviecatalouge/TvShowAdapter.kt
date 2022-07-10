package com.example.moviecatalouge

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.movie_item.view.*
import kotlinx.android.synthetic.main.tv_show_item.view.*

class TvShowAdapter(
    private val tv : List<TvShow>, val listener: OnAdapterListener
) : RecyclerView.Adapter<TvShowAdapter.TvViewHolder>(){

    class TvViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindTv(Tv : TvShow){
            itemView.tvShow_title.text = Tv.name
            itemView.tvShow_release_date.text = Tv.first_air_date
            itemView.tvShow_description.text = Tv.overview
            itemView.tvShow_vote_average.text = Tv.vote_average
            Glide.with(itemView).load(IMAGE_BASE + Tv.poster_path).into(itemView.tvShow_poster)
            Log.e("TvShowAdapter", "URL Image ==> $IMAGE_BASE${Tv.poster_path}")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvViewHolder {
        return TvViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.tv_show_item, parent, false))
    }

    override fun getItemCount(): Int = tv.size

    override fun onBindViewHolder(holder: TvViewHolder, position: Int) {
        holder.bindTv(tv[position])
        holder.itemView.setOnClickListener {
            listener.onClick(tv[position]) }
    }

    interface OnAdapterListener {
        fun onClick(result: TvShow)
    }
}
