package com.example.expenseassist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.expenseassist.data.Shop
import com.example.expenseassist.ui.ShopViewModel
import com.example.expenseassist.ui.theme.ExpenseAssistTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val shopViewModel: ShopViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExpenseAssistTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShopListScreen(shopViewModel)
                }
            }
        }

        // Kiểm tra và thêm dữ liệu mẫu chỉ khi cơ sở dữ liệu trống
        shopViewModel.insertSampleDataIfEmpty()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopListScreen(viewModel: ShopViewModel) {
    var shops by remember { mutableStateOf(emptyList<Shop>()) }
    var showAddDialog by remember { mutableStateOf(false) }
    var shopName by remember { mutableStateOf("") }
    var shopAddress by remember { mutableStateOf("") }
    var shopPhone by remember { mutableStateOf("") }
    var refreshTrigger by remember { mutableStateOf(false) }
    var addTrigger by remember { mutableStateOf(false) }

    // Lấy dữ liệu từ ViewModel khi Composable khởi tạo hoặc cần làm mới
    LaunchedEffect(Unit, refreshTrigger, addTrigger) {
        shops = withContext(Dispatchers.IO) {
            viewModel.getAllShops()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Danh sách cửa hàng") },
                navigationIcon = {
                    IconButton(onClick = { /* Xử lý quay lại nếu cần */ }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Text("+")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            // Nút để làm mới danh sách
            Button(
                onClick = {
                    refreshTrigger = !refreshTrigger // Thay đổi trigger để kích hoạt LaunchedEffect
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Làm mới danh sách")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Danh sách cửa hàng
            LazyColumn {
                items(shops) { shop ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp)
                            .clickable {
                                // Xử lý khi nhấn vào cửa hàng (ví dụ: hiển thị chi tiết)
                            },
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Tên: ${shop.name}",
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Text(
                                text = "Địa chỉ: ${shop.address}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "SĐT: ${shop.phone}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }

    // Dialog để thêm cửa hàng mới
    if (showAddDialog) {
        AlertDialog(
            onDismissRequest = { showAddDialog = false },
            title = { Text("Thêm cửa hàng mới") },
            text = {
                Column {
                    TextField(
                        value = shopName,
                        onValueChange = { shopName = it },
                        label = { Text("Tên cửa hàng") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = shopAddress,
                        onValueChange = { shopAddress = it },
                        label = { Text("Địa chỉ") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = shopPhone,
                        onValueChange = { shopPhone = it },
                        label = { Text("Số điện thoại") },
                        modifier = Modifier.fillMaxWidth(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        if (shopName.isNotBlank() && shopAddress.isNotBlank() && shopPhone.isNotBlank()) {
                            viewModel.insertShop(Shop(name = shopName, address = shopAddress, phone = shopPhone))
                            // Kích hoạt làm mới danh sách bằng trigger
                            addTrigger = !addTrigger
                            shopName = ""
                            shopAddress = ""
                            shopPhone = ""
                            showAddDialog = false
                        }
                    }
                ) {
                    Text("Thêm")
                }
            },
            dismissButton = {
                Button(onClick = { showAddDialog = false }) {
                    Text("Hủy")
                }
            }
        )
    }
}