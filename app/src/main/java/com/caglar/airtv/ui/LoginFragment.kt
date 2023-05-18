package com.caglar.airtv.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.caglar.airtv.R
import com.caglar.airtv.databinding.FragmentLoginBinding
import okhttp3.*
import java.io.IOException


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var editTextPhoneNum: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Fragment layoutunu inflate etmek için kullanılır
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        editTextPhoneNum = view.findViewById(R.id.editTextPhone1)
        editTextPassword = view.findViewById(R.id.editTextPassword2)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Fragment'ın oluşturulduğu view oluşturulduktan sonra çağrılır

        // Gerekli UI elemanlarını burada tanımlayacağım
        val button = view.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            println("aaaa")

            performLogin()
        }
    }

    private fun performLogin() {

        
        val client = OkHttpClient()
        val phonenum = editTextPhoneNum.text.toString()
        val password = editTextPassword.text.toString()
        val baseUrl = "https://airtvplayer.com/admin/api/register.php"
        val brand = ""
        val model = ""
        val androidId = ""
        val city = ""
        val country_name = ""
        val latitude = ""
        val longitude = ""
        val org = ""

        val registerurl = "$baseUrl?phonenum=$phonenum&brand=$brand&model=$model&androidid=$androidId"
        val URL: String = "https://airtvplayer.com/admin/api/register.php"

        if (URL.isNotEmpty()) {
            val request = Request.Builder()
                .url(registerurl)
                .get()
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                }

                override fun onResponse(call:Call, response: Response) {
                    Log.i("Response", "Recieved Response from server")
                    response.use {
                        if (!response.isSuccessful) {
                            Log.e("HTTP Error", "Something didn't load, or wasn't successful")
                        } else {
                            println(phonenum)
                            println(password)
                        }
                    }
                }
            })
        } else {
            println("url boş")
        }
    }


}