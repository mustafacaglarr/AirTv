package com.caglar.airtv


import androidx.lifecycle.MutableLiveData
import com.caglar.airtv.models.AddPackageRequestModel
import com.caglar.airtv.models.PackageData
import com.caglar.airtv.models.VerifyData
import com.google.gson.Gson
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException



class ApiClass {

    var verified = MutableLiveData<Boolean>()
    var packageAdded = MutableLiveData<Boolean>()
    var client = OkHttpClient()
    val url = ""


    fun verifyDevice(androidId: String) {
        val request = Request.Builder()
            .url(url + "?androidid=" + androidId)
            .get()
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                this@ApiClass.verified.postValue(false)

                //Kayıtlı değil - Register gerekli
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    var tempString = response.body!!.string()
                    var jObject = JSONObject(tempString.substring(1, tempString.lastIndex))
                    var responseModel = Gson().fromJson(jObject.toString(), VerifyData::class.java)
                    if (responseModel != null && responseModel.id != null && responseModel.id.isNotEmpty() && responseModel.active == "1") {
                        //Cihaz Kayıtlı - Main Sayfasına Yönlendir
                        this@ApiClass.verified.postValue(true)
                        Constants.phoneNumber = responseModel.phonenum
                    } else {
                        this@ApiClass.verified.postValue(false)

                        //Kayıtlı değil - Register gerekli
                    }
                } catch (e: java.lang.Exception) {
                    e.printStackTrace()
                    this@ApiClass.verified.postValue(false)

                    //Kayıtlı değil - Register gerekli
                }

            }

        })

    }

    fun AddPackage(packageData: PackageData) {
        val phonenum = Constants.phoneNumber
        val packageId = packageData.packageId
        val password = ""
        if (packageData.isPaidContent) {
            println("ücretli")

        }
        var model = AddPackageRequestModel(phonenum ?: "", packageId, password)
        val mediaType = "application/json".toMediaType()
        var tmp = Gson().toJson(model)
        val requestBody = tmp.toString().toRequestBody(mediaType)

        val url = "https://airtvplayer.com/public/admin/public/api/channel/add-package-to-user"
        val request = Request.Builder()
            .url(url)
            .post(requestBody)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("başarısızzz")
                this@ApiClass.packageAdded.postValue(false)
            }

            override fun onResponse(call: Call, response: Response) {
                println("başarılııı")
                this@ApiClass.packageAdded.postValue(true)
            }
        })

    }

}