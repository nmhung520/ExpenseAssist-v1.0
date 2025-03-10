/*
* Giải thích:
@Database: Định nghĩa cơ sở dữ liệu với các bảng Shop và Item.
version = 1: Phiên bản cơ sở dữ liệu (tăng lên nếu có thay đổi cấu trúc sau này).
getDatabase: Singleton pattern để đảm bảo chỉ có một instance của cơ sở dữ liệu.
* */
package com.example.expenseassist.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Shop::class, Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expense_assist_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}