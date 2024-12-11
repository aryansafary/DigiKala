package com.arysapp.digikala.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.arysapp.digikala.data.db.DigikalaDatabase
import com.arysapp.digikala.data.db.FavoriteListDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoriteDaoModule {

    @Provides
    @Singleton
    fun provideFavoriteListDao(
        database: DigikalaDatabase
    ): FavoriteListDao = database.FavoriteListDao()
}