package com.caglar.airtv.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.Constants
import com.caglar.airtv.R
import com.caglar.airtv.adapter.PackageAdapter
import com.caglar.airtv.models.PackageData
import com.caglar.airtv.models.ControlPackageResponse
import com.caglar.airtv.models.PackagesResponseModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException


class KaynakFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PackageAdapter

    lateinit var root: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kaynak, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        root = view
        fetchPackages()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        adapter = PackageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun fetchPackages() {
        val url = "https://airtvplayer.com/public/admin/public/api/channel/getAllPackages"
        val mediaType = "application/json".toMediaType()
        val requestBody = "{\"country\":\"TR\"}".toRequestBody(mediaType)
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                // Hata durumunda yapılacak işlemler
                println("hataa")
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                var responseModel = Gson().fromJson(responseBody,PackagesResponseModel::class.java)
                if (responseModel!=null &&responseModel.responseResult==true&&responseModel.responseData.isNotEmpty()) {
                    println("Paket listesi dolu.")
                    Constants.allPackages.clear()
                    Constants.allPackagesOld = responseModel.responseData
                    for (item in responseModel.responseData){
                        ControlPackages(item)
                    }
                } else {
                    println("Paket listesi boş.")
                }
            }
        })
    }

    private fun ControlPackages(packageData: PackageData){

        val phonenum = Constants.phoneNumber
        val packageId = packageData.packageId

        val requestBodyJson = JSONObject().apply {
            put("phone", phonenum)
            put("packageId", packageId)
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
                // Hata durumunda yapılacak işlemler
                packageData.isPaired = false
                println("hataa")
            }

            override fun onResponse(call: Call, response: Response) {
                println("control başarılı")
                val responseModel = Gson().fromJson(response.body?.string(), ControlPackageResponse::class.java)
                if (responseModel != null && responseModel.responseResult == true && responseModel.responseData == true){
                    println("paket eşli")
                    packageData.isPaired = true
                //paket eşli
                }else{
                    println("paket eşli değil")
                    packageData.isPaired = false
                //paket eşli değil
                }
                Constants.allPackages.add(packageData)
                if (Constants.allPackagesOld.count()==Constants.allPackages.count()){
                    showPackages(Constants.allPackages)

                }
            }
        })
    }

    private fun showPackages(packages: List<PackageData>) {
        activity?.runOnUiThread {
            adapter.setPackages(packages)
            recyclerView.adapter = adapter
            adapter.addClick.observe(root.findViewTreeLifecycleOwner()!!, Observer {
                if (it != null){
                    Constants.apiClass.AddPackage(it)
                }
            })
            adapter.detailClick.observe(root.findViewTreeLifecycleOwner()!!,Observer{
                println("diğer fragmente geçiş")
                val bundle = Bundle()
                bundle.putInt("packageId", it.packageId)
                println(it.packageId)
                Constants.navController?.navigate(R.id.action_kaynakFragment_to_kaynakDetailFragment, bundle)

            })
        }
    }


}