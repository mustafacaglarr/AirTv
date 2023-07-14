package com.caglar.airtv

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.caglar.airtv.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView
    private val fragmentKaynak = KaynakFragment()
    private val fragmentArama = AramaFragment()
    private val fragmentListe = ListeFragment()
    private val fragmentHesap = HesapFragment()
    private val firstFragment = FirstFragment()
    private val LoginFragment=  LoginFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        bottomNavigationView = findViewById(R.id.bottom_navigation)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer2) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_kaynak -> {
                    println("1")
                    fragmentcagir(fragmentKaynak)
                    true
                }
                R.id.navigation_arama -> {
                    println("2")
                    fragmentcagir(fragmentArama)
                    true

                }
                R.id.navigation_liste -> {
                    println("3")
                    fragmentcagir(fragmentListe)
                    true
                }
                R.id.navigation_hesap -> {
                    println("4")
                    fragmentcagir(fragmentHesap)
                    true
                }
                else -> false
            }
        }

        fragmentManager.beginTransaction().apply {
            add(R.id.fragmentContainer2, fragmentHesap, "5")

            add(R.id.fragmentContainer2, fragmentListe, "4")

            add(R.id.fragmentContainer2, fragmentArama, "3")

            add(R.id.fragmentContainer2, fragmentKaynak, "2")
            add(R.id.fragmentContainer2, LoginFragment, "1")
            add(R.id.fragmentContainer2, firstFragment, "0")

        }.commit()




    }

    fun fragmentcagir(fragment: Fragment){
        println("geçiş")
        val gecis = supportFragmentManager.beginTransaction()
        gecis.replace(R.id.fragmentContainer2,fragment)
        gecis.commit()
    }




    }

