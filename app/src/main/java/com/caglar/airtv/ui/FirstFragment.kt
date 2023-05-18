package com.caglar.airtv.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.caglar.airtv.R
import com.google.android.material.button.MaterialButton


class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var button3: MaterialButton
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view:View=inflater.inflate(R.layout.fragment_first, container, false)
        button3 = view.findViewById(R.id.button1)
        button3.setOnClickListener {
            println("aaa")
            Navigation.findNavController(view).navigate(R.id.action_firstFragment_to_loginFragment)
        }


        return view
    }


}