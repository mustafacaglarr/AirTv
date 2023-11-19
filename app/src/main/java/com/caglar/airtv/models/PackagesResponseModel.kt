package com.caglar.airtv.models

data class PackagesResponseModel(
    var responseResult: Boolean,
    var responseMessage: String,
    var responseData: ArrayList<PackageData>
)
