package com.padcx.shared.data.vos

data class AddressVO (
    var id : String? = null,
    var address : String? = null
)fun AddressVO.toAddressMap():HashMap<String,String?> {
    val addressMap = hashMapOf(
        "id" to id,
        "address" to address
    )
    return addressMap
}