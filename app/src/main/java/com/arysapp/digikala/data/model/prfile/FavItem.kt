package com.arysapp.digikala.data.model.prfile

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arysapp.digikala.util.Constants.FAVORITE_LIST_TABLE

@Entity(tableName = FAVORITE_LIST_TABLE)
data class FavItem(
    @PrimaryKey
    val id: String,
    val discountPercent: Int,
    val image: String,
    val name: String,
    val price: Long,
    val seller: String,
    val star: Double
)