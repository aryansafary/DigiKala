package com.arysapp.digikala.di

import android.content.Context
import com.arysapp.digikala.data.datastore.DataStoreImpl
import com.arysapp.digikala.data.datastore.DataStoreRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ):DataStoreRepository = DataStoreImpl(context = context)
}