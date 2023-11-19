package com.caglar.airtv

import android.annotation.SuppressLint

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import com.caglar.airtv.ui.login.FirstFragment



class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "HardwareIds")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mId = Settings.Secure.getString(contentResolver,Settings.Secure.ANDROID_ID)

        val firstFragment = FirstFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer1, firstFragment)
            .commit()


    }




}




