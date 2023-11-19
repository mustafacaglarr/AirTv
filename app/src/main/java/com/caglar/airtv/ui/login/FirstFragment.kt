package com.caglar.airtv.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import com.caglar.airtv.ApiClass
import com.caglar.airtv.MainActivity2
import com.caglar.airtv.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class FirstFragment : Fragment(R.layout.fragment_first) {
    private lateinit var apiClass:ApiClass
    var mId: String = ""
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

        apiClass= ApiClass()
        mId = Settings.Secure.getString(activity?.contentResolver, Settings.Secure.ANDROID_ID)

        val button1: Button = view.findViewById(R.id.button1)
        val button2: Button = view.findViewById(R.id.button2)

        button1.setOnClickListener {
            // giriş yap button
            verifiedData()
        }

        button2.setOnClickListener {
            //kanal kaynakları button
            requireActivity().run{
                println("button2")
                verifiedData()


            }
        }
    }

    private fun verifiedData(){
        apiClass.verified.observe(viewLifecycleOwner, Observer {
            if (it){
                //register olmacak, zaten kayıtlı
                startActivity(Intent(context, MainActivity2::class.java))
                println("kayıtlııı")
                println(mId)
            }else{
                val secondFragment = LoginFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer1, secondFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        apiClass.verifyDevice(mId)
    }
}