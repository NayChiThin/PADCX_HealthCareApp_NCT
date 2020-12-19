package com.padcx.doctor.delegates

import com.padcx.shared.data.vos.ConsultRequestVO

interface ConsultRequestItemDelegate {
    fun onTapAccept(consultRequest:ConsultRequestVO)
}