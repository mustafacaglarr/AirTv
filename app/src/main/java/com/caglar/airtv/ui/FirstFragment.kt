package com.caglar.airtv.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.caglar.airtv.R
import com.google.android.material.button.MaterialButton


class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var navController: NavController
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
        val navController = findNavController(view)
        val button1: Button = view.findViewById(R.id.button1)
        val button2: Button = view.findViewById(R.id.button2)
        button1.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_kaynakFragment)
        }

        button2.setOnClickListener {
            navController.navigate(R.id.action_firstFragment_to_loginFragment)
        }
    }

}