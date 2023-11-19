package com.caglar.airtv.models

data class PackageData(
    val packageId: Int,
    val packageName: String,
    val isPrivate: Boolean,
    val packageDesc: String,
    val isPaidContent: Boolean,
    val preview: String,
    var isPaired: Boolean? = false
)
