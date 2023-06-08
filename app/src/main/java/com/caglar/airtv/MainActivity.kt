package com.caglar.airtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.caglar.airtv.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private val fragmentKaynak = KaynakFragment()
    private val fragmentArama = AramaFragment()
    private val fragmentListe = ListeFragment()
    private val fragmentHesap = HesapFragment()
    private val firstFragment = FirstFragment()

    private val fragmentManager: FragmentManager = supportFragmentManager
    private var activeFragment: Fragment = fragmentKaynak

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_kaynak -> {
                    switchFragment(fragmentKaynak)
                    true
                }
                R.id.navigation_arama -> {
                    switchFragment(fragmentArama)
                    true
                }
                R.id.navigation_liste -> {
                    switchFragment(fragmentListe)
                    true
                }
                R.id.navigation_hesap -> {
                    switchFragment(fragmentHesap)
                    true
                }
                else -> false
            }
        }
        fragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer, fragmentHesap, "4")
            hide(fragmentHesap)
            add(R.id.fragmentContainer, fragmentListe, "3")
            hide(fragmentListe)
            add(R.id.fragmentContainer, fragmentArama, "2")
            hide(fragmentArama)
            add(R.id.fragmentContainer, fragmentKaynak, "1")
            add(R.id.fragmentContainer, firstFragment, "0")
        }.commit()
        switchFragment(firstFragment)


    }
    private fun switchFragment(fragment: Fragment) {
        if (fragment == firstFragment) {
            bottomNavigationView.visibility = View.GONE
        } else {
            bottomNavigationView.visibility = View.VISIBLE
        }
        fragmentManager.beginTransaction().hide(activeFragment).show(fragment).commit()
        activeFragment = fragment
    }
    }

