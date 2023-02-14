package com.example.integrador

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment : Fragment() {

    private var swiperefresh: SwipeRefreshLayout? = null
    val TAG = "MainActivity"
    var data: ArrayList<NewsResponse.Data> = ArrayList()


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

        ApiRest.initService()


        val toolbar = (requireActivity() as MainActivity).findViewById<TextView>(R.id.txtPaginaTitulo)
        toolbar.text = "Valorant news"

        val mLayoutManager = GridLayoutManager(context, 2)
        val rvAgents = view.findViewById<RecyclerView>(R.id.rvNews)

        rvAgents.layoutManager = mLayoutManager

        adapter = NewsAdapter(data) { new ->

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
        getNewsInfo()

        val searchView = view.findViewById<SearchView>(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Called when the user submits the query
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val searchText = searchView.query.toString()

                val filteredData = if (!newText.isNullOrEmpty()) {
                    data.filter { it.attributes.title.contains(searchText, true) }
                } else {
                    data
                }

                adapter?.updateData(ArrayList(filteredData))

                return true
            }

        })

    }

    fun getNewsInfo() {
        val call = ApiRest.service.getNews("Bearer ${ApiRest.apiKey}")
        call.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(call: Call<NewsResponse>, response: Response<NewsResponse>) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    Log.i(TAG, body.toString())
                    data.clear()
                    data.addAll(body.data.sortedByDescending { it.attributes.publishedAt })
                    Log.i(TAG, data.toString())
                    adapter?.notifyDataSetChanged()
// Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Error")
                }

                swiperefresh?.isRefreshing = false
            }
            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, t.message.toString())

                swiperefresh?.isRefreshing = false
            }
        })

    }
    fun emptyArrayList(): ArrayList<NewsResponse.Data> {
        return ArrayList()
    }

}