package com.example.expenseassist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController

class MainScreenCode : ViewModel() {
    // Manage the state of the shopping list (items)
    val shoppingList = mutableStateListOf("Coke", "Apples", "Gloves")

    // Manage the state of the shop list
    val shopList = mutableStateListOf<String>()

    // Manage the visibility state of the DropdownMenu
    private val _showMenu = mutableStateOf(false)
    val showMenu: Boolean
        get() = _showMenu.value

    // Function to toggle the DropdownMenu visibility
    fun toggleMenu() {
        _showMenu.value = !_showMenu.value
    }

    // Function to dismiss the DropdownMenu
    fun dismissMenu() {
        _showMenu.value = false
    }

    // Function to navigate to the "Add a shop" screen
    fun onAddShopClicked(navController: NavHostController) {
        dismissMenu()
        navController.navigate("addShop")
        println("Tapped Add a shop")
    }

    // Function to navigate to the "Add an item" screen
    fun onAddItemClicked(navController: NavHostController) {
        dismissMenu()
        navController.navigate("addItem")
        println("Tapped Add an item")
    }

    // Function to add a new shop to the shop list
    fun addShop(shopName: String) {
        shopList.add(shopName)
    }

    // Function to add a new item to the shopping list
    fun addItem(itemName: String) {
        shoppingList.add(itemName)
    }
}