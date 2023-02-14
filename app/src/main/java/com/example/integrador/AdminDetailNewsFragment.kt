package com.example.integrador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminDetailNewsFragment : Fragment() {

    val TAG = "MainActivity"

    val key = "420ddc0472813e7e96ed2d2dd1bda42334ea27ac4e279cdaa80194d4ea28efcb5782daff9e00cf7a84edf0a0131ef36dad3d64acc29755aad48bb96d829f2ac90e423f55fe0b93eb51cadde3be5aa06ee9c5ff6fecdcf7bf06cdd1e3f3982f70ea5dee42479e0525aae703ca0fbb33f10f8757b77cc8459e154d3f5502c0d079"

    lateinit var new: NewsResponse.Data

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_admin_detail_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        new = (arguments?.getSerializable("new") as? NewsResponse.Data)!!

        val tvTit = view.findViewById<EditText>(R.id.txtTituloEdit)
        val tvSub = view.findViewById<EditText>(R.id.txtSub)
        val tvCont = view.findViewById<EditText>(R.id.txtContenido)
        val ivImagen = view.findViewById<EditText>(R.id.ivImagenContenido)

        if (new != null) {
            activity?.findViewById<TextView>(R.id.txtPaginaTitulo)!!.text = new?.attributes?.title

            tvTit.setText(new?.attributes?.title)
            tvSub.setText(new?.attributes?.subtitle)
            tvCont.setText(new?.attributes?.content)

            ivImagen.setText(new?.attributes?.urlImages)
        } else {
            tvSub.setText("TEST")
        }
        val editSaveButton: Button = view.findViewById(R.id.btSaveNew)
        editSaveButton.setOnClickListener {

                saveNew(tvTit.text.toString(), tvSub.text.toString(), tvCont.text.toString(), ivImagen.text.toString(),)


            }
    }
    fun saveNew(title: String, subtitle: String, context: String, imageUrl: String) {

        val updateNew = ApiRest.upNew(title,subtitle,context,imageUrl)

        val call = ApiRest.service.updateNew(newId = new?.id.toString(), request = updateNew, apikey = key)

        call.enqueue(object : Callback<EditNewResponse> {
            override fun onResponse(call: Call<EditNewResponse>, response: Response<EditNewResponse>) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    var editedResponse = response.body()
                    print(editedResponse)
                    goToFragment(NewsFragment())



// Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Error")
                }

            }
            override fun onFailure(call: Call<EditNewResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())

            }
        })
    }
    fun goToFragment(fragment: Fragment) {
        activity?.let {
            it.supportFragmentManager.beginTransaction().addToBackStack(null)
                .replace(R.id.mainContainer, fragment).commit()
        }
    }
}