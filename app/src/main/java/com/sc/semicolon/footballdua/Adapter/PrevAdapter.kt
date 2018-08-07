package com.sc.semicolon.footballdua.Adapter

import android.content.Context
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.semicolon.footballdua.Model.Prev
import com.sc.semicolon.footballdua.R
import kotlinx.android.synthetic.main.row_match.view.*

/**
 * Created by cis on 27/07/2018.
 */
class PrevAdapter(private val context: Context?, private val items: List<Prev>, private val listener: (Prev) -> Unit )
    : RecyclerView.Adapter<PrevAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_match, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Prev, listener: (Prev) -> Unit) {
            itemView.tv_tanggal.text = items.dateEvent
            itemView.tv_team_home.text = items.teamHome
            itemView.tv_team_away.text = items.teamAway
            itemView.tv_score_home.text = items.scoreHome
            itemView.tv_score_away.text = items.scoreAway
            itemView.setOnClickListener { listener(items) }
        }
    }
}