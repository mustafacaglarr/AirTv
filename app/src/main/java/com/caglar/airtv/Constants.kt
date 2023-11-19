package com.caglar.airtv

import androidx.navigation.NavController
import com.caglar.airtv.models.ChannelData
import com.caglar.airtv.models.PackageData

object Constants {
    var phoneNumber: String? = null
    var allPackages = ArrayList<PackageData>()
    var allPackagesOld = ArrayList<PackageData>()
    var apiClass = ApiClass()
    var navController: NavController? = null
    var alldetailchannel=ArrayList<ChannelData>()


}