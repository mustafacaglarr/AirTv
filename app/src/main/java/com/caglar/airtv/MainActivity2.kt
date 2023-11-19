package com.caglar.airtv

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.caglar.airtv.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity2 : FragmentActivity() {
    private lateinit var navController: NavController
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val  bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottom_navigation)

        navController = findNavController(R.id.navigation_layout)
        Constants.navController = navController


        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
            R.id.navigation_kaynak->replaceFragment(0)
            R.id.navigation_arama->replaceFragment(1)
            R.id.navigation_liste->replaceFragment(2)
            R.id.navigation_hesap->replaceFragment(3)

            }
            true
        }


    }

    private fun replaceFragment(fragmentId: Int) {
        if (fragmentId == 0) Constants.navController?.navigate(R.id.kaynakFragment)
        else if (fragmentId == 1) Constants.navController?.navigate(R.id.aramaFragment)
        if (fragmentId == 2) Constants.navController?.navigate(R.id.listeFragment)
        if (fragmentId == 3) Constants.navController?.navigate(R.id.hesapFragment)
    }


}