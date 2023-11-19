package com.caglar.airtv.ui.login

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.caglar.airtv.ApiClass
import com.caglar.airtv.Constants
import com.caglar.airtv.MainActivity2
import com.caglar.airtv.R
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class LoginFragment : Fragment() {
    private lateinit var editTextPhoneNum: EditText
    private lateinit var editTextPassword: EditText
    var mId: String = ""


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        editTextPhoneNum = view.findViewById(R.id.editTextPhoneNum)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        mId = Settings.Secure.getString(activity?.contentResolver,Settings.Secure.ANDROID_ID)
        val button3: Button = view.findViewById(R.id.button3)
        button3.setOnClickListener {
            fetchPackages()
        }


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun fetchPackages() {
        val client = OkHttpClient()
        val phonenum = editTextPhoneNum.text.toString()
        val password = editTextPassword.text.toString()
        val baseUrl = ""
        val Model = Build.MODEL
        val model = Model
        val manufacturer = Build.MANUFACTURER
        val brand = manufacturer
        val registerurl = "$baseUrl?phonenum=$phonenum&brand=$brand&model=$model&androidid=$mId"
        if (baseUrl.isNotEmpty()){
            val request = Request.Builder()
                .url(registerurl)
                .get()
                .build()

            client.newCall(request).enqueue(object : okhttp3.Callback{
                override fun onFailure(call: okhttp3.Call, e: IOException) {
                    e.printStackTrace();
                }

                override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                    Log.i("Response","Recieved Response from serverrrr");
                    response.use {
                        if (!response.isSuccessful){
                            Log.e("HTTP Error","Something didn't load , or wasn't successful");

                        }else{
                            println(phonenum)
                            println(password)
                            Constants.phoneNumber = phonenum
                            startActivity(Intent(context, MainActivity2::class.java))

                        }
                    }
                }
            })
        }else{
            println("url boş")
        }
    }

}