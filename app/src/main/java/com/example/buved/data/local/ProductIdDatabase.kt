package com.example.buved.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [ProductIdEntity::class, CartItemEntity::class], version= 2, exportSchema = false)
abstract class ProductIdDatabase: RoomDatabase() {
    abstract fun productIdDao(): ProductIdDao
}