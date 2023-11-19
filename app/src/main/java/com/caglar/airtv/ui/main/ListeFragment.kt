package com.caglar.airtv.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.caglar.airtv.R
import com.caglar.airtv.adapter.DetailAdapter

import okhttp3.*


class ListeFragment : Fragment() {


    var mId: String = ""

    @SuppressLint("HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mId = Settings.Secure.getString(activity?.contentResolver, Settings.Secure.ANDROID_ID)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_liste, container, false)
        return view
    }




}