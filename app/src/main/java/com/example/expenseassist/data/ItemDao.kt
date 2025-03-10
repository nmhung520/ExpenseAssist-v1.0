/*
* Giải thích:
insertItem: Thêm một Item vào cơ sở dữ liệu.
getItemsByShop: Lấy danh sách các Item theo shopId.
* */
package com.example.expenseassist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ItemDao {
    @Insert
    suspend fun insertItem(item: Item)

    @Query("SELECT * FROM items WHERE shopId = :shopId")
    suspend fun getItemsByShop(shopId: Int): List<Item>
}