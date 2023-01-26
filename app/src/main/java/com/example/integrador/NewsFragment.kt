package com.example.integrador

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class NewsFragment : Fragment() {




    private var adapter: NewsAdapter? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = (requireActivity() as MainActivity).findViewById<TextView>(R.id.txtPaginaTitulo)
        toolbar.text = "Valorant news"

        val mLayoutManager = GridLayoutManager(context, 2)
        val rvAgents = view.findViewById<RecyclerView>(R.id.rvNews)

        rvAgents.layoutManager = mLayoutManager

        adapter = NewsAdapter(getNewsInfo()) { new ->

            activity?.let {
                val fragment = NewsDetailFragment()
                fragment.arguments = Bundle()
                fragment.arguments?.putSerializable("new", new)

                print(new)

                it.supportFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.mainContainer, fragment).commit()


            }

        }
        adapter?.notifyDataSetChanged()
        rvAgents.adapter = adapter

    }

    fun getNewsInfo(): ArrayList<NewsJson.Result> {
        val gson = Gson()
        val jsonFile = context?.assets?.open("news.json")
        val jsonString = if (jsonFile != null) {
            jsonFile.bufferedReader().use { it.readText() }
        } else {
            null
        }
        print(jsonString)
        if (jsonString == null) {
            return emptyArrayList()
        } else {

            val news: NewsJson = gson.fromJson(jsonString, NewsJson::class.java)
            return ArrayList<NewsJson.Result>(news.results)

        }

    }
    fun emptyArrayList(): ArrayList<NewsJson.Result> {
        return ArrayList()
    }

}