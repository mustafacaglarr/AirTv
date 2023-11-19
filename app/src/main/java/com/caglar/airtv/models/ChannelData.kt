package com.caglar.airtv.models

import java.io.Serializable

data class ChannelData(
    val cname:String,
    val clink:String,
    val logoUrl:String,
    val nowProgramInfo:Boolean,
    val nextProgramInfo:Boolean,
    val channel_content:Boolean

) : Serializable
