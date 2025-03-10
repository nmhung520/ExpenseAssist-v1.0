/*
* Giải thích:
@Entity(tableName = "items"): Định nghĩa bảng items trong cơ sở dữ liệu.
shopId: Là trường để liên kết mặt hàng với cửa hàng (Shop).
* */
package com.example.expenseassist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val price: Double,
    val shopId: Int // Foreign key liên kết với Shop
)