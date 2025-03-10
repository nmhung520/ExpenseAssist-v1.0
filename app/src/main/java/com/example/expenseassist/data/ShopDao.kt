/*
* Giải thích:
@Dao: Đánh dấu đây là một Data Access Object để Room sử dụng.
insertShop: Thêm một Shop vào cơ sở dữ liệu.
getAllShops: Truy vấn để lấy tất cả các Shop.
* */
package com.example.expenseassist.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ShopDao {
    @Insert
    suspend fun insertShop(shop: Shop)

    @Query("SELECT * FROM shops")
    suspend fun getAllShops(): List<Shop>
}