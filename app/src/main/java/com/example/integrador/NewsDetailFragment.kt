package com.example.integrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso

class NewsDetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val new = arguments?.getSerializable("new") as? NewsJson.Result


        val tvSub = view.findViewById<TextView>(R.id.txtSub)
        val tvCont = view.findViewById<TextView>(R.id.txtContenido)
        val ivImagen = view.findViewById<ImageView>(R.id.ivImagenContenido)

        if (new != null) {
            activity?.findViewById<TextView>(R.id.txtPaginaTitulo)!!.text = new.title

            tvSub.text = new.description.subtitle
            tvCont.text = new.content

            Picasso.get().load(new.description.imageofContent).into(ivImagen)
        } else {
            tvSub.text = "TEST"
        }






    }
}
