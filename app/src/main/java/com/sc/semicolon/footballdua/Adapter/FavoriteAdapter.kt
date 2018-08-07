package com.sc.semicolon.footballdua.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.semicolon.footballdua.Helper.Favorite
import com.sc.semicolon.footballdua.R
import kotlinx.android.synthetic.main.row_match.view.*

/**
 * Created by cis on 30/07/2018.
 */
class FavoriteAdapter(private val items: List<Favorite>, private val listener: (Favorite) -> Unit )
    : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_match, parent, false))

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Favorite, listener: (Favorite) -> Unit) {
            itemView.tv_tanggal.text = items.tanggal
            itemView.tv_team_home.text = items.teamHome
            itemView.tv_team_away.text = items.teamAway
            itemView.tv_score_home.text = items.scoreHome
            itemView.tv_score_away.text = items.scoreAway
            itemView.setOnClickListener { listener(items) }
        }
    }
}