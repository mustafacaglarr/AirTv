package com.caglar.airtv.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.caglar.airtv.R
import com.caglar.airtv.adapter.PackageAdapter
import com.caglar.airtv.data.PackageData
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class KaynakFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PackageAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kaynak, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        fetchPackages()
    }

    private fun setupRecyclerView() {
        adapter = PackageAdapter()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
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
            }

            override fun onResponse(call: Call, response: Response) {
                val responseBody = response.body?.string()
                val packageList = parsePackages(responseBody)
                activity?.runOnUiThread {
                    showPackages(packageList)
                }
            }
        })
    }

    private fun parsePackages(responseBody: String?): List<PackageData> {
        val packages = mutableListOf<PackageData>()
        try {
            val jsonObject = JSONObject(responseBody)
            val responseData = jsonObject.getJSONArray("responseData")

            for (i in 0 until responseData.length()) {
                val packageObj = responseData.getJSONObject(i)
                val packageId = packageObj.getInt("packageId")
                val packageName = packageObj.getString("packageName")
                val isPrivate = packageObj.getBoolean("isPrivate")
                val packageDesc = packageObj.getString("packageDesc")
                val isPaidContent = packageObj.getBoolean("isPaidContent")
                val preview = packageObj.getString("preview")

                val packageData = PackageData(packageId, packageName, isPrivate, packageDesc, isPaidContent, preview)
                packages.add(packageData)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return packages
    }

    private fun showPackages(packages: List<PackageData>) {
        adapter.setPackages(packages)
    }
}