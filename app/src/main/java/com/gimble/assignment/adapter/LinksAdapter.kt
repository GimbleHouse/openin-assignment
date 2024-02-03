package com.gimble.assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.gimble.assignment.R
import com.gimble.assignment.data.remote.dashboardapi.DashboardData
import com.gimble.assignment.data.remote.dashboardapi.TopLink
import com.squareup.picasso.Picasso

class LinksAdapter(val data : List<TopLink>): RecyclerView.Adapter<LinksAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinksAdapter.MyViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.card_linkdata,parent,false)
        return MyViewHolder(itemview)
    }



    override fun onBindViewHolder(holder: LinksAdapter.MyViewHolder, position: Int) {
      val stuff= data[position]
            holder.namer.text=stuff.title
            holder.url.text=stuff.web_link
            holder.count.text=stuff.total_clicks.toString()
            val link = stuff.original_image
            holder.time.text=stuff.times_ago
            Picasso.get().load(link).into(holder.img)


    }

    override fun getItemCount(): Int {
        return data.count()
    }
    class MyViewHolder (itemview : View): RecyclerView.ViewHolder(itemview) {

        val count : TextView = itemview.findViewById(R.id.clickss)
        val namer : TextView = itemview.findViewById(R.id.nameolink)
        val url : TextView =itemview.findViewById(R.id.linktextview)
        val img : ImageView = itemview.findViewById(R.id.linkpicture)
        val time : TextView=itemview.findViewById(R.id.timeolink)

    }
}
