package com.example.integrador

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment() {

    lateinit var etUser : EditText
    lateinit var inputLayout :TextInputLayout
    lateinit var password: String

    val TAG = "MainActivity"
    var data: ArrayList<UsersResponse.User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ApiRest.initService()
        //navView = activity?.findViewById(R.id.bottom_nav)!!
        //navView.visibility = View.GONE
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.isVisible = false

        val toolbar =
            (requireActivity() as MainActivity).findViewById<TextView>(R.id.txtPaginaTitulo)
        toolbar.text = "LOGIN"

        //val textWatcher = object : TextWatcher {
        //    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            //override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            //    if (s?.length == 0) {
            //        inputLayout.error = "No puede estar vacio"
            //    } else if (!s.toString().matches("^[a-zA-Z0-9._-]{3,}$".toRegex())) {
            //        inputLayout.error = "Entrada inválida"
            //    } else {
            //        inputLayout.error = null
            //    }
            //}

        //    override fun afterTextChanged(s: Editable?) {}
        //}


        etUser = view.findViewById(R.id.etUser)
        inputLayout = view.findViewById(R.id.input_layout)
        //etUser.addTextChangedListener(textWatcher)
        password = view.findViewById<EditText>(R.id.etPassword).text.toString()


        val loginButton: Button = view.findViewById(R.id.btLogearse)
        loginButton.setOnClickListener {
            if (inputLayout.error == null) {
                password = view.findViewById<EditText>(R.id.etPassword).text.toString()
                login()


            } else {
                val builder = AlertDialog.Builder(this@LoginFragment.context)
                builder.setTitle("Error")
                builder.setMessage(inputLayout.error)
                builder.setPositiveButton("OK", null)
                val dialog: AlertDialog = builder.create()
                dialog.show()
            }
        }



        view.findViewById<TextView>(R.id.btRegistro).setOnClickListener {

            activity?.let {
                it.supportFragmentManager.beginTransaction().addToBackStack(null)
                    .replace(R.id.mainContainer, RegisterFragment()).commit()

            }

        }
    }

    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.isVisible = true

    }

    fun login() {

        val loginRequest = ApiRest.LoginRequest(etUser.text.toString(), password)

        val call = ApiRest.service.doLogin(loginRequest)

       call.enqueue(object : Callback<UsersResponse> {
            override fun onResponse(call: Call<UsersResponse>, response: Response<UsersResponse>) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    var loginResponse = response.body()
                    print(loginResponse)
                    if (loginResponse != null) {
                        if(loginResponse.user.admin) {

                            goToFragment(AdminNewsFragment())

                        } else {
                            goToFragment(NewsFragment())
                        }
                    }



// Imprimir aqui el listado con logs
                } else {
                    Log.e(TAG, response.errorBody()?.string() ?: "Error")
                }

            }
            override fun onFailure(call: Call<UsersResponse>, t: Throwable) {
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