package com.example.integrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottom_navigation_view = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, NewsFragment()).commit()


        bottom_navigation_view.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btHome -> {
                    goToFragment(NewsFragment())
                    true
                }
                R.id.btLogin -> {
                    goToFragment(LoginFragment())
                    true
                }
                else -> false
            }
        }
        bottom_navigation_view.selectedItemId = R.id.btHome

    }

    fun goToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.mainContainer, fragment).commit()
    }
}