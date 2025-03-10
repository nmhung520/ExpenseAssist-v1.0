package com.example.expenseassist.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.expenseassist.data.Shop
import com.example.expenseassist.data.ShopRepository
import kotlinx.coroutines.launch

class ShopViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ShopRepository(application)

    fun insertShop(shop: Shop) = viewModelScope.launch {
        repository.insertShop(shop)
    }

    suspend fun getAllShops(): List<Shop> {
        return repository.getAllShops()
    }

    fun insertSampleDataIfEmpty() = viewModelScope.launch {
        val currentShops = repository.getAllShops()
        if (currentShops.isEmpty()) {
            insertShop(Shop(name = "Shop A", address = "123 Street", phone = "0901234567"))
            insertShop(Shop(name = "Shop B", address = "456 Avenue", phone = "0907654321"))
        }
    }
}