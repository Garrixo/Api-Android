package com.example.integrador

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class NewsAdapter(private var data: ArrayList<NewsResponse.Data>, val onClick:(NewsResponse.Data) ->Unit) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
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

        fun bind(item: NewsResponse.Data) {

            tvTitulo.text = item.attributes.title
            tvFecha.text = "Publicado: "+ item.attributes.publishedAt.substring(8,10) + "/" + item.attributes.publishedAt.substring(5,7) + "/" + item.attributes.publishedAt.substring(0,4)
            Picasso.get().load(item.attributes.urlImages).into(ivImagen)



            itemView.setOnClickListener {
                Log.v("Pulso sobre", item.attributes.title)

                onClick(item)
            }
        }

    }

    fun updateData(newData: ArrayList<NewsResponse.Data>) {
        data = newData
        notifyDataSetChanged()
    }
}