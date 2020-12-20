package com.padcx.padcx_healthcareapp_nct.delegates

import com.padcx.shared.data.vos.ConsultVO

interface ConsultHistoryItemDelegate {
    fun onTapSendMessage(consult:ConsultVO)
    fun onTapPrescription(consult: ConsultVO)
}