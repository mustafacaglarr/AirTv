package com.caglar.airtv.models

data class ChannelResponseModel(
    var responseResult: Boolean,
    var responseMessage: String,
    var responseData: ArrayList<ChannelData>
)
