package com.example.integrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, LoginFragment()).commit()
        val bottom_navigation_view = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)



        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btHome -> {
                    goToFragment(NewsFragment())
                    true
                }
                R.id.btUsuario -> {
                    goToFragment(UserInfoFragment())
                    true
                }

                else -> false
            }
        }
     //   bottom_navigation_view.selectedItemId = R.id.btHome
    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
    }
}