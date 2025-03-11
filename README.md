# ExpenseAssist-v1.0

## Mục tiêu
Ứng dụng quản lý danh sách cửa hàng, cho phép người dùng:
- Xem danh sách cửa hàng.
- Thêm cửa hàng mới (tên, địa chỉ, số điện thoại).
- Mở rộng: Xem chi tiết cửa hàng, so sánh giá, xuất JSON.

## Công nghệ
- **Giao diện**: Jetpack Compose (Kotlin), không dùng XML.
- **Lưu trữ**: Room Database.
- **Quản lý dữ liệu**: ViewModel.
- **Dependencies chính**:
  - Compose BOM: `2023.10.01`.
  - Room: `2.6.1`.
  - Navigation Compose: `2.7.7`.
  - Lifecycle ViewModel Compose: `2.8.0`.

## Tiến trình hiện tại
- Đã hiển thị danh sách cửa hàng bằng `LazyColumn`.
- Đã thêm chức năng thêm cửa hàng mới qua `AlertDialog` với `TextField`.
- Đã sửa lỗi `KeyboardOptions` bằng cách dùng `import androidx.compose.foundation.text.KeyboardOptions`.
- Đã cập nhật file `build.gradle.kts` để dùng Compose BOM.

## Các bước tiếp theo
- Thêm màn hình chi tiết khi nhấn vào một cửa hàng.
- Thêm chức năng so sánh giá giữa các cửa hàng.
- Xuất danh sách cửa hàng thành JSON.
