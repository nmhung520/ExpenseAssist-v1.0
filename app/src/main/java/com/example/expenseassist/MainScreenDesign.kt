package com.example.expenseassist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenDesign(navController: NavHostController, mainScreenCode: MainScreenCode = viewModel()) {
    Scaffold(
        floatingActionButton = {
            Box {
                FloatingActionButton(onClick = { mainScreenCode.toggleMenu() }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
                DropdownMenu(
                    expanded = mainScreenCode.showMenu,
                    onDismissRequest = { mainScreenCode.dismissMenu() },
                    modifier = Modifier.background(Color.White.copy(alpha = 0.3f))
                ) {
                    // Item "Add a shop"
                    DropdownMenuItem(
                        text = { Text("Add a shop") },
                        onClick = { mainScreenCode.onAddShopClicked(navController) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .highlightOnClick()
                    )
                    // Item "Add an item"
                    DropdownMenuItem(
                        text = { Text("Add an item") },
                        onClick = { mainScreenCode.onAddItemClicked(navController) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .highlightOnClick()
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            // Display the shop list
            Text(
                text = "Shops",
                style = MaterialTheme.typography.headlineMedium
            )
            if (mainScreenCode.shopList.isEmpty()) {
                Text(
                    text = "No shops added yet",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                LazyColumn {
                    items(mainScreenCode.shopList) { shop ->
                        Text(
                            text = shop,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display the shopping list
            Text(
                text = "Shopping List",
                style = MaterialTheme.typography.headlineMedium
            )
            if (mainScreenCode.shoppingList.isEmpty()) {
                Text(
                    text = "No items in the list",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            } else {
                LazyColumn {
                    items(mainScreenCode.shoppingList) { item ->
                        Text(
                            text = item,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
        }
    }
}