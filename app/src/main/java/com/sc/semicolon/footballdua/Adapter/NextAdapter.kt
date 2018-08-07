package com.sc.semicolon.footballdua.Adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sc.semicolon.footballdua.Model.Prev
import com.sc.semicolon.footballdua.R
import kotlinx.android.synthetic.main.row_match.view.*
import kotlinx.android.synthetic.main.row_match2.view.*

/**
 * Created by cis on 28/07/2018.
 */
class NextAdapter(private val context: Context?, private val items: List<Prev>, private val listener: (Prev) -> Unit )
    : RecyclerView.Adapter<NextAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_match2, parent, false))

    override fun onBindViewHolder(holder:ViewHolder, position: Int) {
        holder.bindItem(items[position],listener)
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

        fun bindItem(items: Prev, listener: (Prev) -> Unit) {
            itemView.tv_event.text = items.event
            itemView.tv_tanggal2.text = items.dateEvent
            itemView.setOnClickListener { listener(items) }
        }
    }
}