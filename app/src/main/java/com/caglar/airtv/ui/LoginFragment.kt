package com.caglar.airtv.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.data.Paket
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException


class LoginFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var paketlerAdapter: PaketlerAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        paketlerAdapter = PaketlerAdapter(emptyList())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = paketlerAdapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPaketler()
    }

    private fun getPaketler() {
        val url = "https://airtvplayer.com/public/admin/public/api/channel/getAllPackages"
        val requestBody = "{\"country\":\"TR\"}"
        val mediaType = "application/json; charset=utf-8".toMediaType()

        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .post(requestBody.toRequestBody(mediaType))
            .build()


        client.newCall(request).enqueue(object : okhttp3.Callback{
            override fun onFailure(call: okhttp3.Call, e: IOException) {
                e.printStackTrace();
            }

            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                val responseBody = response.body?.string()
                Log.i("Response","Recieved Response from server");
                response.use {
                    if (!response.isSuccessful&& responseBody != null){
                        val paketListesi =parsePaketListesi(responseBody)
                        paketlerAdapter = PaketlerAdapter(paketListesi)
                        recyclerView.adapter = paketlerAdapter
                        Log.e("HTTP Error","Something didn't load , or wasn't successful");

                    }else{
                        println("başarılı")

                    }
                }
            }
        })
    }

    private fun parsePaketListesi(json: String): List<Paket> {
        val gson = Gson()
        val response = gson.fromJson(json, Response::class.java)
        return response.responseData
    }

    data class Response(
        val responseResult: Boolean,
        val responseMessage: String,
        val responseData: List<Paket>
    )
}