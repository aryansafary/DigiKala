package com.arysapp.digikala.data.db

import androidx.room.*
import com.arysapp.digikala.data.model.prfile.FavItem
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteListDao {
    @Query("SELECT * FROM favorite_list_table")
    fun getAllFavoriteItems() : Flow<List<FavItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteItem(favItem: FavItem)

    @Delete
    suspend fun removeFavoriteItem(favItem: FavItem)

    @Query("DELETE FROM favorite_list_table ")
    suspend fun clearFavoriteList()

    @Query("SELECT EXISTS(SELECT * FROM favorite_list_table WHERE id = :itemId)")
    fun isFavoriteItemExist(itemId: String): Flow<Boolean>

}