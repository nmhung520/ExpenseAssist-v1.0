/*
* Giải thích:
ShopRepository: Lớp trung gian giữa ViewModel và DAO, giúp quản lý các thao tác dữ liệu.
* */
package com.example.expenseassist.data


import android.content.Context

class ShopRepository(context: Context) {
    private val shopDao: ShopDao
    private val itemDao: ItemDao

    init {
        val database = AppDatabase.getDatabase(context)
        shopDao = database.shopDao()
        itemDao = database.itemDao()
    }

    suspend fun insertShop(shop: Shop) = shopDao.insertShop(shop)
    suspend fun getAllShops() = shopDao.getAllShops()
    suspend fun insertItem(item: Item) = itemDao.insertItem(item)
    suspend fun getItemsByShop(shopId: Int) = itemDao.getItemsByShop(shopId)
}