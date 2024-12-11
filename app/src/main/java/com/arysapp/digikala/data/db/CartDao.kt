package com.arysapp.digikala.data.db

import androidx.room.*
import com.arysapp.digikala.data.model.basket.CartItem
import com.arysapp.digikala.data.model.basket.CartStatus
import kotlinx.coroutines.flow.Flow


@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCartItem(cart: CartItem)

    @Query("select * from shopping_cart where cartStatus=:status")
    fun getAllItems(status: CartStatus): Flow<List<CartItem>>

    @Delete
    suspend fun removeFromCart(item: CartItem)

    @Query("DELETE FROM shopping_cart where cartStatus=:status")
    fun deleteAllItems(status: CartStatus)

    @Query("update shopping_cart set count=:newCount where itemId=:id")
    suspend fun changeCountCartItem(id: String, newCount: Int)


    @Query("update shopping_cart set cartStatus=:newCartStatus where itemId=:id")
    suspend fun changeStatusCart(id: String, newCartStatus: CartStatus)


    @Query("select total(count) as count from shopping_cart where cartStatus=:status")
    fun getCartItemsCount(status: CartStatus): Flow<Int>

    @Query("select total(count) as count from shopping_cart where itemId = :itemId")
    fun getItemsCountInBasket(itemId: String): Flow<Int>


    @Query("SELECT EXISTS(SELECT * FROM shopping_cart WHERE itemId = :itemId)")
    fun isItemExistInBasket(itemId: String): Flow<Boolean>

}