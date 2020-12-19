package com.padcx.shared.data.vos

import java.io.Serializable

data class AddressVO (
    var id : String? = "",
    var address : String? = ""
):Serializable
fun AddressVO.toAddressMap():HashMap<String,String?> {
    val addressMap = hashMapOf(
        "id" to id,
        "address" to address
    )
    return addressMap
}