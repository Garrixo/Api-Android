package com.example.integrador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private val data: ArrayList<NewsJson.Result>, val onClick:(NewsJson.Result) ->Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
    override fun getItemCount(): Int = data.size


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitulo = itemView.findViewById<TextView>(R.id.txtTitulo)
        val tvFecha = itemView.findViewById<TextView>(R.id.txtFecha)
        val ivImagen = itemView.findViewById<ImageView>(R.id.ivNews)

        fun bind(item: NewsJson.Result) {

            tvTitulo.text = item.title
            tvFecha.text = item.date
            Picasso.get().load(item.description.imageofContent).into(ivImagen)



            itemView.setOnClickListener {
                Log.v("Pulso sobre", item.title)

                onClick(item)
            }
        }

    }
}