package com.padcx.shared.persistence.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.padcx.shared.data.vos.CheckoutVO

@Dao
interface CheckoutDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCheckout(checkout:CheckoutVO)
}