package com.caglar.airtv.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.caglar.airtv.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var bottomNavigationView: BottomNavigationView
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)


        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bottomNavigationView = requireActivity().findViewById(R.id.bottom_navigation)
        bottomNavigationView.visibility = View.GONE

        val button1: Button = view.findViewById(R.id.button1)
        val button2: Button = view.findViewById(R.id.button2)

        button1.setOnClickListener {
            // İlk butona tıklandığında yapılacak işlemler
            val secondFragment = LoginFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer2, secondFragment)
                .addToBackStack(null)
                .commit()
        }

        button2.setOnClickListener {
            // İkinci butona tıklandığında yapılacak işlemler
            val thirdFragment = KaynakFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer2, thirdFragment)
                .addToBackStack(null)
                .commit()

            val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView?.visibility = View.VISIBLE
        }
    }



}