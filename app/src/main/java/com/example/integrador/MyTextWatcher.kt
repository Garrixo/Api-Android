package com.example.integrador

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.textfield.TextInputLayout

class MyTextWatcher(private val textInputLayout: TextInputLayout) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {
        when (textInputLayout.id) {

            R.id.etName-> {
                if (s.isEmpty()) {
                    textInputLayout.error = "Name is required"
                } else {
                    textInputLayout.error = null
                }
            }
            R.id.etSurname -> {
                if (s.isEmpty()) {
                    textInputLayout.error = "Surname is required"
                } else {
                    textInputLayout.error = null
                }
            }
            R.id.etEmail -> {
                if (s.isEmpty()) {
                    textInputLayout.error = "Invalid email address"
                } else {
                    textInputLayout.error = null
                }
            }
            R.id.etUserRegister -> {
                if (s.isEmpty()) {
                    textInputLayout.error = "User is required"
                } else {
                    textInputLayout.error = null
                }
            }
            R.id.etPasswordRegister -> {
                if (s.isEmpty()) {
                    textInputLayout.error = "Password is required"
                } else if (s.length < 8) {
                    textInputLayout.error = "Password must be at least 8 characters long"
                } else {
                    textInputLayout.error = null
                }
            }
        }

    }
}
