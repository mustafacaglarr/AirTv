package com.caglar.airtv.data

data class Paket(
    val packageId: Int,
    val packageName: String,
    val isPrivate: Boolean,
    val packageDesc: String,
    val isPaidContent: Boolean,
    val preview: String
)
