package com.caglar.airtv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.caglar.airtv.ui.LoginFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var editTextPhoneNum: EditText
    private lateinit var editTextPassword: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextPhoneNum = findViewById(R.id.editTextPhoneNum)
        editTextPassword = findViewById(R.id.editTextPassword)
        val button = findViewById<Button>(R.id.button1)
        button.setOnClickListener {
            val fragment = LoginFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit()

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
            val URL : String = "https://airtvplayer.com/admin/api/register.php "

            if (URL.isNotEmpty()){
                val request = Request.Builder()
                    .url(registerurl)
                    .get()
                    .build()

                client.newCall(request).enqueue(object : okhttp3.Callback{
                    override fun onFailure(call: okhttp3.Call, e: IOException) {
                        e.printStackTrace();
                    }

                    override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                        Log.i("Response","Recieved Response from server");
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
    }

