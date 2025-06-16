package com.example.buved.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buved.domain.model.ProductId

@Entity(tableName = "productId")
data class ProductIdEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: String
)

fun ProductIdEntity.toDomain() = ProductId(
    id,
    productId
)

fun ProductId.toEntity() = ProductIdEntity(
    id,
    productId
)