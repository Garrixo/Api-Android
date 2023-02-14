package com.example.integrador

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RegisterFragment : Fragment() {


    val TAG = "MainActivity"
    lateinit var inputLayoutName: TextInputEditText
    lateinit var inputLayoutSurname: TextInputEditText
    lateinit var inputLayoutEmail: TextInputEditText
    lateinit var inputLayoutUser: TextInputEditText
    lateinit var inputLayoutPassword: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {




        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ApiRest.initService()

        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.isVisible = false




        inputLayoutName= view.findViewById(R.id.etName)
        inputLayoutSurname= view.findViewById(R.id.etSurname)
        inputLayoutEmail = view.findViewById(R.id.etEmail)
        inputLayoutUser = view.findViewById(R.id.etUserRegister)
        inputLayoutPassword= view.findViewById(R.id.etPasswordRegister)
        val brRegister: Button = view.findViewById(R.id.btRegister)




        val toolbar =
            (requireActivity() as MainActivity).findViewById<TextView>(R.id.txtPaginaTitulo)
        toolbar.text = "REGISTER"



        fun isValidEmail(email: String): Boolean {
            val pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }

        fun isValidName(): Boolean {
            return inputLayoutName.text.toString().isNotEmpty()
        }

        fun isValidSurname(): Boolean {
            return inputLayoutSurname.text.toString().isNotEmpty()
        }

        fun isValidEmail(): Boolean {
            val email = inputLayoutEmail.text.toString().trim()
            return email.isNotEmpty() && isValidEmail(email)
        }

        fun isValidUser(): Boolean {
            return inputLayoutUser.text.toString().isNotEmpty()
        }

        fun isValidPassword(): Boolean {
            val password = inputLayoutPassword.text.toString().trim()
            return password.isNotEmpty() && password.length >= 8
        }


        if(isValidName() && isValidEmail() && isValidUser() && isValidSurname() && isValidPassword()) {


        }


        view.findViewById<Button>(R.id.btRegister).isEnabled = true
        view.findViewById<Button>(R.id.btRegister).setOnClickListener {
            if (isValidName() && isValidSurname() && isValidEmail() && isValidUser() && isValidPassword()) {


                register()



            }
        }


        view.findViewById<TextView>(R.id.btIrAlLogin).setOnClickListener {

            activity?.let {it.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.mainContainer, LoginFragment()).commit()

            }



        }

    }

    override fun onStop() {
        super.onStop()
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation_view)?.isVisible = true

    }

    fun register() {

        val registerRequest = ApiRest.RegisterRequest(inputLayoutUser.context.toString(), inputLayoutEmail.context.toString(), inputLayoutPassword.context.toString(), false)

        val call = ApiRest.service.doRegister(registerRequest)

        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                val body = response.body()

                if (response.isSuccessful && body != null) {
                    var registerResponse = response.body()
                    print(registerResponse)
                    if (registerResponse != null) {
                        if(registerResponse.user.admin) {

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
            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
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