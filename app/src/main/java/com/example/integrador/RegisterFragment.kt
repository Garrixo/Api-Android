package com.example.integrador

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout


class RegisterFragment : Fragment() {

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

        val inputLayoutName: TextInputLayout = view.findViewById(R.id.etName)
        val inputLayoutSurname: TextInputLayout = view.findViewById(R.id.etSurname)
        val inputLayoutEmail: TextInputLayout = view.findViewById(R.id.etEmail)
        val inputLayoutUser: TextInputLayout = view.findViewById(R.id.etUserRegister)
        val inputLayoutPassword: TextInputLayout = view.findViewById(R.id.etPasswordRegister)
        val btIrAlLogin: Button = view.findViewById(R.id.btIrAlLogin)




        val toolbar =
            (requireActivity() as MainActivity).findViewById<TextView>(R.id.txtPaginaTitulo)
        toolbar.text = "REGISTER"



        fun isValidEmail(email: String): Boolean {
            val pattern = Patterns.EMAIL_ADDRESS
            return pattern.matcher(email).matches()
        }

        fun isValidName(): Boolean {
            return inputLayoutName.editText?.text.toString().isNotEmpty()
        }

        fun isValidSurname(): Boolean {
            return inputLayoutSurname.editText?.text.toString().isNotEmpty()
        }

        fun isValidEmail(): Boolean {
            val email = inputLayoutEmail.editText?.text.toString().trim()
            return email.isNotEmpty() && isValidEmail(email)
        }

        fun isValidUser(): Boolean {
            return inputLayoutUser.editText?.text.toString().isNotEmpty()
        }

        fun isValidPassword(): Boolean {
            val password = inputLayoutPassword.editText?.text.toString().trim()
            return password.isNotEmpty() && password.length >= 8
        }


        fun isAllValid(): Boolean {
            return !inputLayoutName.isErrorEnabled &&
                    !inputLayoutSurname.isErrorEnabled &&
                    !inputLayoutEmail.isErrorEnabled &&
                    !inputLayoutUser.isErrorEnabled &&
                    !inputLayoutPassword.isErrorEnabled
        }


        view.findViewById<Button>(R.id.btRegistro).isEnabled = isAllValid()
        view.findViewById<Button>(R.id.btRegistro).setOnClickListener {
            if (isValidName() && isValidSurname() && isValidEmail() && isValidUser() && isValidPassword()) {
                // proceed with login
            }
        }


        view.findViewById<TextView>(R.id.btIrAlLogin).setOnClickListener {

            activity?.let {it.supportFragmentManager.beginTransaction().addToBackStack(null).replace(R.id.mainContainer, LoginFragment()).commit()

            }



        }

    }

}