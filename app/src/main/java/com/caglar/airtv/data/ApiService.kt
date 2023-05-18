package com.caglar.airtv.data

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("/admin/api/register.php")
    fun registerUser(@Field("phonenum") phonenum: String): Call<RegistrationResponse>
}
data class RegistrationResponse(
    val success: Boolean,
    val message: String
)