package com.caglar.airtv.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class LoginFragment : Fragment() {
    private val baseUrl = "https://airtvplayer.com/admin/api/"
    private val registerUrl = "${baseUrl}register.php"
    private val myIpUrl = "${baseUrl}myip.php"
    private lateinit var editTextPhoneNum: EditText
    private lateinit var editTextPassword: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        editTextPhoneNum = view.findViewById(R.id.editTextPhoneNum)
        editTextPassword = view.findViewById(R.id.editTextPassword)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button3: Button = view.findViewById(R.id.button3)




        button3.setOnClickListener {
            fetchPackages()
            println("button")
            val fragment = KaynakFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer2, fragment)
                .addToBackStack(null)
                .commit()



        }

    }
    private fun fetchPackages() {
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
        val org  = ""
        val registerurl = "$baseUrl?phonenum=$phonenum&brand=$brand&model=$model&androidid=$androidId"
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

                        }
                    }
                }
            })
        }else{
            println("url boş")
        }
    }







}