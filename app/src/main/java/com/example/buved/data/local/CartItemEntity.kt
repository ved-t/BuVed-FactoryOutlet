package com.example.buved.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buved.domain.model.CartItem


@Entity(tableName = "cartItem")
data class CartItemEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val productId: Int,
    val productQuantity: Int
)

fun CartItemEntity.toDomain() = CartItem(
    id,
    productId,
    productQuantity
)


fun CartItem.toEntity() = CartItemEntity(
    id,
    productId,
    productQuantity
)