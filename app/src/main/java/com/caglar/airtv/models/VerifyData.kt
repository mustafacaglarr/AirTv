package com.caglar.airtv.models

data class VerifyData(
    val id: String,
    val user_id: String?,
    val androidid: String,
    val phonenum: String,
    val username: String?,
    val address: String,
    val active: String,
    val city: String,
    val country_name: String,
    val latitude: String,
    val longitude: String,
    val org: String,
    val brand: String,
    val model: String,
    val tarih: String,
    val created_at: String,
    val updated_at: String,
    val deleted_at: String?,

)
