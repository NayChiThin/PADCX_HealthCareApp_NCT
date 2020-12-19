package com.padcx.doctor.delegates

import com.padcx.shared.data.vos.ConsultVO

interface ConsultationItemDelegate {
    fun onTapPrescription(consult:ConsultVO)
    fun onTapCaseSummary(consult: ConsultVO)
    fun onTapConsultNote(consult: ConsultVO)
    fun onTapSendMessage(consult: ConsultVO)
}