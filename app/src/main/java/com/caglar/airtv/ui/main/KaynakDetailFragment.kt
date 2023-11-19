package com.caglar.airtv.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.Constants
import com.caglar.airtv.R
import com.caglar.airtv.adapter.DetailAdapter
import com.caglar.airtv.models.ChannelData
import com.caglar.airtv.models.ChannelResponseModel
import com.caglar.airtv.models.PackageData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class KaynakDetailFragment:Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailAdapter
    //var detailClick = MutableLiveData<PackageData>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        arguments?.getInt("packageId")?.let {
            getPackageChannels(it)


        }?:run {
            Toast.makeText(context, "Paket Bulunamadı!", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kaynak_detail, container, false)
        val bottomNavigationView = activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView?.visibility = View.GONE
        recyclerView = view.findViewById(R.id.detailrecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = DetailAdapter()

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    private fun getPackageChannels(pId: Int){

        val requestBodyJson = JSONObject().apply {
            put("packageId", pId)
        }

        val mediaType = "application/json".toMediaType()
        val requestBody = requestBodyJson.toString().toRequestBody(mediaType)

        val url = ""
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("liste başarısız")
            }

            override fun onResponse(call: Call, response: Response) {
                //modeli tanımlayacaksın
                val responseBody = response.body?.string()
                var responseModel = Gson().fromJson(responseBody,ChannelResponseModel::class.java)
                if (responseModel!=null && responseModel.responseResult == true &&  responseModel.responseData.isNotEmpty()){
                    println("control başarılıı")
                    Constants.alldetailchannel.clear()
                    Constants.alldetailchannel = responseModel.responseData
                    showChannels()

                }else{
                    println("control başarısızz")
                }
            }
        })

    }

    private fun showChannels() {
        activity?.runOnUiThread {
            adapter.setChannel(Constants.alldetailchannel)
            recyclerView.adapter = adapter

        }
    }


}